<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { useRoute, RouterLink } from "vue-router";
import { useBoardStore } from "@/stores/boardStore";
import { useUserStore } from "@/stores/userStore";

import util from "@/common/util.js";
import http from "@/common/axios.js";

//example components
// import DefaultNavbar from "../../../examples/navbars/NavbarDefault.vue";
import DefaultFooter from "../../../examples/footers/FooterDefault.vue";

//image
import bg0 from "@/assets/img/bg9.jpg";

//dep
import Typed from "typed.js";

const route = useRoute();
const body = document.getElementsByTagName("body")[0];
const happy = ref(0);
const normal = ref(0);
const sad = ref(0);
const upset = ref(0);

//hooks
onMounted(() => {
    body.classList.add("about-us");
    body.classList.add("bg-gray-200");

    if (document.getElementById("typed")) {
        // eslint-disable-next-line no-unused-vars
        var typed = new Typed("#typed", {
            stringsElement: "#typed-strings",
            typeSpeed: 90,
            backSpeed: 90,
            backDelay: 200,
            startDelay: 500,
            loop: true,
        });
    }
});

onUnmounted(() => {
    body.classList.remove("about-us");
    body.classList.remove("bg-gray-200");
});

const { userStore, setLogin, setRememberId } = useUserStore();
let isLogin = sessionStorage.getItem("isLogin");
let isRememberId = sessionStorage.getItem("isRemeberId");

const { boardStore, setBoardDetail, boardList } = useBoardStore();
boardStore.boardId = sessionStorage.getItem("boardId");

const changeToDelete = async () => {
    try {
        // let { data } = await http.delete("/boards/" + route.params.boardId);
        let { data } = await http.delete("/boards/" + boardStore.boardId);
        console.log(data);
        alert("글이 삭제되었습니다.");
        boardList();
    } catch (error) {
        console.log("BoardMainVue: error : ");
        console.log(error);
    }
};

const getDetail = async () => {
    try {
        // let { data } = await http.get("/boards/" + route.params.boardId);
        let { data } = await http.get("/boards/" + boardStore.boardId);

        console.log("getDetail : ");
        console.log(data);

        console.log(data);
        setBoardDetail(data);

        console.log(boardStore.sameUser);
        if (sessionStorage.getItem("userId") == data.userId) {
            boardStore.sameUser = true;
            console.log("같은 작성자");
            console.log(boardStore.sameUser);
        } else {
            boardStore.sameUser = false;
            console.log("작성자와 로그인한 사용자가 일치하지 않습니다.");
            console.log(boardStore.sameUser);
        }
    } catch (error) {
        console.error(error);
    }
};

const getReactionDetail = async () => {
    try {
        let { data } = await http.get("/boards/reaction/" + boardStore.boardId);
        happy.value = data.happy;
        normal.value = data.normal;
        sad.value = data.sad;
        upset.value = data.upset;

        console.log(boardStore.sameUser);
    } catch (error) {
        console.error(error);
    }
    getDetail();
};

const reactionUpdate = async () => {
    let reaction = {
        boardId: boardStore.boardId,
        happy: happy.value,
        normal: normal.value,
        sad: sad.value,
        upset: upset.value,
    };
    try {
        let { data } = await http.post("/boards/reaction", reaction);

        console.log(data);
    } catch (error) {
        console.error(error);
    }
    getReactionDetail();
};

getDetail();
getReactionDetail();
</script>

<template>
    <header class="bg-gradient-dark">
        <div class="page-header min-vh-75" :style="{ backgroundImage: `url(${bg0})` }">
            <span class="mask bg-gradient-dark opacity-6"></span>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 text-center mx-auto my-auto">
                        <h1 class="text-white">
                            한국 최고의 여행지는 <span class="text-white" id="typed"></span>
                        </h1>
                        <div id="typed-strings">
                            <h1>제주도</h1>
                            <h1>부산</h1>
                            <h1>서울</h1>
                        </div>
                        <p class="lead mb-4 text-white opacity-8">
                            한국 최고의 여행지를 추천해주세요<br />
                            여행지 후기를 서로 공유해봐요!
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="card card-body shadow-xl mx-3 mx-md-4 mt-n6">
        <router-link to="/pages/landing-pages/about-us">> 뒤로 가기</router-link>
        <h5>글 상세</h5>
        <table class="table">
            <tbody>
                <tr>
                    <td>글번호</td>
                    <td id="boardIdDetail">{{ boardStore.boardId }}</td>
                </tr>
                <tr>
                    <td>제목</td>
                    <td id="titleDetail">{{ boardStore.boardTitle }}</td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td v-html="boardStore.boardDetail" id="contentDetail"></td>
                </tr>
                <tr>
                    <td>작성자</td>
                    <td id="userNameDetail">{{ boardStore.userName }}</td>
                </tr>
                <tr>
                    <td>작성일시</td>
                    <td id="regDtDetail">
                        {{ util.makeDateStr(boardStore.regDate, "/") }}
                        {{ util.makeTimeStr(boardStore.regTime, ":") }}
                    </td>
                </tr>
            </tbody>
        </table>

        <table>
            <tbody class="d-flex justify-content-around text-align: center;">
                <td>
                    <tr>
                        <!-- <button> -->
                        <img
                            src="@/assets/img/reaction/happy.png"
                            alt="happy"
                            @click="(happy += 1), reactionUpdate()"
                            style="height: 50px; width: 50px"
                        />&nbsp;&nbsp;&nbsp;
                        <!-- Happy -->
                        <!-- </button> -->
                        {{
                            happy
                        }}
                    </tr>
                </td>
                <td>
                    <tr>
                        <img
                            src="@/assets/img/reaction/normal.png"
                            style="height: 50px; width: 50px"
                            @click="(normal += 1), reactionUpdate()"
                        />&nbsp;&nbsp;&nbsp;
                        {{
                            normal
                        }}
                    </tr>
                </td>
                <td>
                    <tr>
                        <img
                            src="@/assets/img/reaction/sad.png"
                            style="height: 50px; width: 50px"
                            @click="(sad += 1), reactionUpdate()"
                        />&nbsp;&nbsp;&nbsp;
                        {{
                            sad
                        }}
                    </tr>
                </td>
                <td>
                    <tr>
                        <img
                            src="@/assets/img/reaction/upset.png"
                            style="height: 50px; width: 50px"
                            @click="(upset += 1), reactionUpdate()"
                        />&nbsp;&nbsp;&nbsp;
                        {{
                            upset
                        }}
                    </tr>
                </td>
            </tbody>
        </table>

        <div class="d-flex flex-row-reverse">
            <router-link :to="{ name: 'updateboard' }"
                ><button
                    v-show="boardStore.sameUser"
                    id="btnBoardUpdateUI"
                    class="btn btn-sm btn-primary btn-outline float-end"
                    style="display: inline !important"
                    type="button"
                >
                    글 수정하기
                </button></router-link
            >

            <RouterLink :to="{ name: 'about' }"
                ><button
                    v-show="boardStore.sameUser"
                    id="btnBoardDeleteUI"
                    @click="changeToDelete"
                    class="btn btn-sm btn-outline-danger ms-1 float-end"
                    type="button"
                >
                    글 삭제하기
                </button></RouterLink
            >
        </div>
    </div>

    <DefaultFooter />
</template>
