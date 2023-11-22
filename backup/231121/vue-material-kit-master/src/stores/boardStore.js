import { reactive, ref, computed } from "vue";
import { defineStore } from "pinia";
import { useRouter } from "vue-router";
import http from "@/common/axios.js";

// store에서 router를 사용하려면 defineStore 안에 선언
// const router = useRouter(); <- 이렇게 하면 안됨 defineStore 안에 해줘야함
export const useBoardStore = defineStore("boardStore", () => {
    const router = useRouter();
    // 로그인 여부, 사용자 이름, 프로필 이미지, 로그인 항목
    const boardStore = reactive({
        limit: 10,
        offset: 0,
        list: [],
        searchWord: "",

        // pagination
        listRowCount: 10,
        pageLinkCount: 10,
        currentPageIndex: 1,
        totalListItemCount: 0, // 총 건 수

        // detail, update, delete
        boardId: 0,
        boardTitle: "",
        boardDetail: "",
        userName: "",
        regDate: "",
        regTime: "",
        // sameUser: false,
    }); // object 형태로 만들거라서 ref 말고 reactive 사용

    const setBoardList = (list) => (boardStore.list = list);
    const setTotalListItemCount = (count) => (boardStore.totalListItemCount = count);
    const setBoardMovePage = (pageIndex) => {
        boardStore.offset = (pageIndex - 1) * boardStore.listRowCount;
        boardStore.currentPageIndex = pageIndex;
    };
    const boardList = async () => {
        let params = {
            limit: boardStore.limit,
            offset: boardStore.offset,
            searchWord: boardStore.searchWord,
        };
        try {
            // /boards 서버에서 받아온 값들을 destructuring 해서 data에 집어넣는다
            let { data } = await http.get("/boards", { params }); // {params:params}의 shorthand property
            console.log("boardStore: data : ");
            console.log(boardStore.searchWord);
            console.log(data);
            setBoardList(data);

            setTotalListItemCount(data.length);
        } catch (error) {
            console.log(error);
        }
    };

    const setBoardDetail = (payload) => {
        boardStore.boardId = payload.boardId;
        boardStore.boardTitle = payload.boardTitle;
        boardStore.boardDetail = payload.boardDetail;
        boardStore.userName = payload.userName;
        boardStore.regDate = payload.boardTime.date;
        console.log(boardStore.regDate);
        boardStore.regTime = payload.boardTime.time;
        boardStore.sameUser = payload.sameUser;
    };

    // pagination
    const pageCount = computed(() =>
        Math.ceil(boardStore.totalListItemCount / boardStore.listRowCount)
    );
    const startPageIndex = computed(() => {
        if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
            //10, 20...맨마지막
            return (
                (boardStore.currentPageIndex / boardStore.pageLinkCount - 1) *
                    boardStore.pageLinkCount +
                1
            );
        } else {
            return (
                Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) *
                    boardStore.pageLinkCount +
                1
            );
        }
    });

    const endPageIndex = computed(() => {
        if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
            //10, 20...맨마지막
            return (
                (boardStore.currentPageIndex / boardStore.pageLinkCount - 1) *
                    boardStore.pageLinkCount +
                boardStore.pageLinkCount
            );
        } else {
            return (
                Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) *
                    boardStore.pageLinkCount +
                boardStore.pageLinkCount
            );
        }
    });

    const prev = computed(() =>
        boardStore.currentPageIndex <= boardStore.pageLinkCount ? false : true
    );
    const next = computed(() =>
        Math.floor(pageCount / boardStore.pageLinkCount) * boardStore.pageLinkCount <
        boardStore.currentPageIndex
            ? false
            : true
    );

    return {
        boardStore,
        boardList,
        setBoardList,
        setBoardDetail,
        setBoardMovePage,
        setTotalListItemCount,
        pageCount,
        startPageIndex,
        endPageIndex,
        prev,
        next,
    };
});
