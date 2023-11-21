<script setup>
import { onMounted, onUnmounted } from "vue";
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

// 내가 수정한 부분import { useUserStore } from "@/stores/userStore";

const { userStore, setLogin, setRememberId } = useUserStore();
let isLogin = sessionStorage.getItem("isLogin");
let isRememberId = sessionStorage.getItem("isRememberId");

const { boardStore, setBoardDetail, boardList } = useBoardStore();

const changeToDelete = async () => {
    try {
        let { data } = await http.delete("/boards/" + boardStore.boardId);
        console.log(data);
        alert("글이 삭제되었습니다.");
        boardList();

        // if (data.result == "login") {
        //   doLogout();
        // } else {
        //   alert("글이 삭제되었습니다.");
        //   boardList();
        // }
    } catch (error) {
        console.log("BoardMainVue: error : ");
        console.log(error);
    }
};

const changeToUpdate = async () => {
    // post form data
    // let formData = new FormData();
    // formData.append("boardId", boardStore.boardId); // update 에 추가
    // formData.append("title", boardStore.boardTitle);
    // formData.append("content", editorData.value); // store X !!!!
    //   console.log("update Page!!!!");
    // let jsonDto = {
    //   boardTitle: boardTitle.value,
    //   boardDetail: editorData.value,
    //   userId: 1,
    //   userName: "woomigyeong",
    // };
    // try {
    //   let { data } = await http.post('/boards/' + boardStore.boardId, jsonDto)
    //   console.log("UpdateModalVue: data : ");
    //   console.log(data);
    //   alert("글이 수정되었습니다.");
    //   // if (data.result == "login") {
    //   //   doLogout();
    //   // } else {
    //   //   alert("글이 등록되었습니다.");
    //   //   closeModal();
    //   // }
    // } catch (error) {
    //   console.log("InsertModalVue: error ");
    //   console.log(error);
    // }
};

const getDetail = async () => {
    try {
        let { data } = await http.get("/boards/" + route.params.boardId);
        // board.value = data.dto;

        console.log("getDetail : ");
        console.log(data);
        // console.log(board)

        setBoardDetail(data);

        console.log(boardStore);

        // if (data.dto.userId == loginStore.userId) {
        //   console.log("작성자와 로그인한 사용자가 일치합니다.");
        //   sameUser = true;
        // } else {
        //   console.log("작성자와 로그인한 사용자가 일치하지 않습니다.");
        // }
    } catch (error) {
        console.error(error);
    }
};

getDetail();
</script>

<template>
    <!-- <DefaultNavbar
        :action="{
            route: 'javascript:;',
            label: 'Buy Now',
            color: 'btn-white',
        }"
        transparent
    /> -->
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
        <div class="d-flex flex-row-reverse">
            <!-- <button
        v-show="boardStore.sameUser"
        id="btnBoardUpdateUI"
        class="btn btn-sm btn-primary btn-outline float-end"
        style="display: inline !important"
        @click="changeToUpdate"
        type="button"
      > -->
            <router-link :to="{ name: 'updateboard' }"
                ><button
                    id="btnBoardUpdateUI"
                    class="btn btn-sm btn-primary btn-outline float-end"
                    style="display: inline !important"
                    @click="changeToUpdate"
                    type="button"
                >
                    글 수정하기
                </button></router-link
            >
            <!-- <button
        v-show="boardStore.sameUser"
        id="btnBoardDeleteUI"
        @click="changeToDelete"
        class="btn btn-sm btn-outline-danger ms-1 float-end"
        type="button"
      > -->

            <RouterLink :to="{ name: 'about' }"
                ><button
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
