<script setup>
import { onMounted, onUnmounted, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import http from "@/common/axios.js";
//example components
// import DefaultNavbar from "../../../examples/navbars/NavbarDefault.vue";
import DefaultFooter from "../../../examples/footers/FooterDefault.vue";

import noimg from "@/assets/img/noImage.png";
//image
import bg0 from "@/assets/img/dg1.jpg";

//dep
import Typed from "typed.js";

// pagination
import PaginationUI from "@/components/PaginationUI.vue";
import util from "@/common/util.js";

// store
import { useBoardStore } from "@/stores/boardStore";

//login 관리
import { useUserStore } from "@/stores/userStore";
const { userStore, setLogin, setRememberId } = useUserStore();

const { boardStore, boardList, setBoardMovePage } = useBoardStore();

const body = document.getElementsByTagName("body")[0];

const likeList = ref([]);
const day = ref(0);
const userId = ref(-1);
const contentId = ref(0);
const contentList = ref([]);
const planList = ref([]);

userId.value = sessionStorage.getItem("userId");
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

const getList = async () => {
    try {
        //관광지 리스트가 내려올 것'
        console.log(userId.value);
        let { data } = await http.get(`likes/detail/${userId.value}`);

        if (data != null) {
            //리스트 저장
            likeList.value = data;
        } else {
            likeList.value = [];
        }
    } catch (error) {
        console.log("error!!!");
        console.log(error);
    }
};

//plan 추가
const addPlan = (contentId, day) => {
    day.value += 1;
    planList.value.push(likeList.value[likeList.value.indexOf(contentId)], day);
    // console.log(likeList.value[likeList.value.indexOf(contentId)]);
    console.log(planList);
    console.log("==>" + likeList.value.indexOf(contentId));
};

const getPlanList = async () => {
    try {
        console.log(userId.value);
        let { data } = await http.get(`/planners/${userId.value}`);
        if (data != null) {
            //리스트 저장

            console.log("============>");
            console.log(data);
            planList.value = data;
        } else {
            planList.value = [];
        }
        console.log(data);
    } catch (error) {
        console.log(error);
    }
};

const insertPlanList = async (curday, curcontentId) => {
    let jsonDto = {
        day: curday,
        contentId: curcontentId,
        userId: userId.value,
    };
    try {
        console.log(userId.value);
        let { data } = await http.post(`/planners/`, jsonDto);

        getPlanList();
        console.log(data);
    } catch (error) {
        console.log(error);
    }
};

const deletePlanList = async () => {
    try {
        console.log(userId.value);
        let { data } = await http.delete(`/planners/${userId.value}`);
        console.log(data);
        day.value = 0;
    } catch (error) {
        console.log(error);
    }
    getPlanList();
};

getList();
getPlanList();
</script>

<template>
    <header class="bg-gradient-dark">
        <div class="page-header min-vh-75" :style="{ backgroundImage: `url(${bg0})` }">
            <span class="mask bg-gradient-dark opacity-3"></span>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 text-center mx-auto my-auto">
                        <h1 class="text-white">
                            <!-- 당신의 여행지는 -->
                            <span class="text-white" id="typed"></span>
                        </h1>
                        <div id="typed-strings">
                            <!-- <h1>부산</h1>
                            <h1>울산</h1>
                            <h1>경남</h1> -->
                            <h1>What Are Your Plan</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class="card card-body shadow-xl mx-3 mx-md-4 mt-n6">
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center mx-auto mb-5 wow" style="max-width: 900px">
                    <!-- <div class="container mt-3"> -->
                    <!-- mt-3 추가 -->

                    <div class="input-group mb-3 mt-3">
                        <!-- mb-3 mt-3  추가 -->
                        <input
                            id="inputSearchWord"
                            type="text"
                            class="form-control"
                            placeholder="검색어를 입력하세요"
                        />
                        <button id="btnSearchWord" class="btn btn-success" type="button">
                            Search
                        </button>
                    </div>

                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>일차</th>
                                <th>사진</th>
                                <th>여행지</th>
                                <th>주소</th>
                            </tr>
                        </thead>
                        <tbody id="boardTbody">
                            <tr v-for="(list, index) in planList">
                                <td>{{ index + 1 }}일차</td>
                                <td>
                                    <img
                                        :src="list.firstImage ? list.firstImage : noimg"
                                        class="card-img-top"
                                        style="height: 50%; width: 50%"
                                        alt="..."
                                    />
                                </td>
                                <td>{{ list.title }}</td>
                                <td>{{ list.addr1 }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <button class="btn btn-success" @click="deletePlanList()">일정 비우기</button>
                    <div class="row">
                        <div
                            class="result card col-lg-3 col-sm my-3"
                            v-for="like in likeList"
                            :key="like.contentId"
                        >
                            <img
                                :src="like.firstImage ? like.firstImage : noimg"
                                class="card-img-top"
                                style="height: 200px"
                                alt="..."
                            />

                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <h5 class="card-title">{{ like.title }}</h5>

                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="30"
                                        height="30"
                                        fill="currentColor"
                                        class="bi bi-plus"
                                        viewBox="0 0 16 16"
                                        @click="insertPlanList((day += 1), like.contentId)"
                                    >
                                        <path
                                            d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"
                                        />
                                    </svg>
                                </div>
                                <p class="card-text mb-6">{{ like.addr1 }} {{ like.addr2 }}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <DefaultFooter />
</template>

<style>
.page-link.active,
.active > .page-link {
    z-index: 3;
    color: greenyellow !important;
    background-color: teal !important;
    border-color: teal !important;
}
</style>
