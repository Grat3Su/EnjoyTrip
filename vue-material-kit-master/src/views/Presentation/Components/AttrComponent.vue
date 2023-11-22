<template>
    <section class="d-flex flex-row flex-wrap">
        <div class="dropdown">
            <button
                class="btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenuButton1"
                data-bs-toggle="dropdown"
                aria-expanded="false"
            >
                {{ sido }}
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <div
                    v-for="list in sidoList"
                    @click="(sido = list.sidoName), (sidoCode = list.sidoCode), getGugun()"
                >
                    <li>
                        <label class="label">
                            {{ list.sidoName }}
                        </label>
                    </li>
                </div>
            </ul>
        </div>
        <div class="dropdown" v-show="sido != '시도'">
            <button
                class="btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenuButton1"
                data-bs-toggle="dropdown"
                aria-expanded="false"
            >
                {{ gugun }}
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <div
                    v-for="list in gugunList"
                    @click="(gugun = list.gugunName), (gugunCode = list.gugunCode), clickGugun()"
                >
                    <li>
                        <label class="label">{{ list.gugunName }}</label>
                    </li>
                </div>
            </ul>
        </div>
    </section>
    <section class="loc d-flex flex-row justify-content-around flex-wrap">
        <div
            class="results row"
            v-for="attr in attrList"
            @click="showDetail()"
            :key="attr.contentId"
        >
            <!-- <div class="results row"> -->
            <div class="card col-md-4" style="width: 18rem">
                <img
                    :src="attr.firstImage ? attr.firstImage : noimg"
                    class="card-img-top"
                    style="height: 200px"
                    alt="..."
                />
                <div class="card-body">
                    <h5 class="card-title">{{ attr.title }}</h5>
                    <p class="card-text">{{ attr.addr1 }} {{ attr.addr2 }}</p>

                    <div class="dropdown">
                        <button
                            class="btn btn-secondary dropdown-toggle"
                            type="button"
                            id="dropdownMenuButton1"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                        >
                            Detail
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li>
                                <div
                                    class="label ms-auto p-2 bd-highlight"
                                    type="text"
                                    style="width: 20rem"
                                >
                                    {{ attr.overview }}
                                </div>
                            </li>
                            <li>
                                <a
                                    class="dropdown-item"
                                    style="background-color: azure"
                                    @click="goMap(attr.addr1)"
                                    >지도에 찍기</a
                                >
                            </li>
                            <li>
                                <a
                                    class="dropdown-item"
                                    style="background-color: yellow"
                                    :href="
                                        'https://map.kakao.com/link/search/' +
                                        attr.addr1 +
                                        ' ' +
                                        attr.addr2
                                    "
                                    >카카오맵 이동</a
                                >
                            </li>
                        </ul>
                        <!-- 하트 찍기 -->
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="30"
                            height="30"
                            :fill="fillheart(attr.contentId) ? 'red' : 'currentColor'"
                            class="bi bi-heart-fill"
                            style="margin-left: 80px"
                            viewBox="0 0 16 16"
                            @click="checkLike(attr.contentId)"
                            v-if="userStore.isLogin"
                        >
                            <path
                                fill-rule="evenodd"
                                d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                            />
                        </svg>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <nav aria-label="Page navigation example">
        <ul class="pagination pagination-lg d-flex justify-content-center">
            <li class="page-item">
                <button class="page-link" @click="clickPage(-1)">&laquo;</button>
            </li>
            <li class="page-item">
                <button class="page-link">{{ page }}</button>
            </li>
            <li class="page-item">
                <button class="page-link" @click="clickPage(1)">&raquo;</button>
            </li>
        </ul>
    </nav>
</template>

<script setup>
import { ref } from "vue";
import http from "@/common/axios.js";
import { useRouter } from "vue-router";
import noimg from "@/assets/img/noImage.png";
import { useUserStore } from "@/stores/userStore";
import { useMapStore } from "@/stores/mapStore";

const router = useRouter();
const { userStore } = useUserStore();
const { mapStore } = useMapStore();

const isShowDetail = ref(false);
const isHeartClick = ref(false);
const page = ref(0);
const attrList = ref([]);
const sido = ref("시도");
const sidoCode = ref(0);
const gugun = ref("구군");
const gugunCode = ref(0);
const sidoList = ref([]);
const gugunList = ref([]);
const likeList = ref([]);

const fillheart = (contentId) => {
    return Array.from(likeList.value).includes(contentId);
};

const goMap = (addr1) => {
    mapStore.searchWord = addr1;
};

const showDetail = () => {
    //showDetail
    isShowDetail.value = true;
    //console.log(isShowDetail.value);
};
const clickPage = (pageindex) => {
    page.value += pageindex;
    getList();
};

const getList = async () => {
    try {
        //관광지 리스트가 내려올 것
        //console.log("attrs/list/" + page.value);
        if (page.value < 0) page.value = 0;

        let { data } = await http.get("attrs/list/" + page.value);
        // //console.log(data);

        if (data != null) {
            //리스트 저장
            attrList.value = data;
            //console.log(attrList.value[0]);
        } else {
            page.value = 0;
            getList();
        }
    } catch (error) {
        //console.log(error);
    }
};
const getSidoList = async () => {
    try {
        //관광지 리스트가 내려올 것
        let { data } = await http.get("attrs/sido");
        //console.log(data);

        if (data != null) {
            //리스트 저장
            sidoList.value = data;
            //console.log(sidoList.value);
        }
    } catch (error) {
        //console.log(error);
    }
};

const getGugun = async () => {
    try {
        //console.log(sidoCode.value);
        //관광지 리스트가 내려올 것'
        let { data } = await http.get("attrs/gugun/" + sidoCode.value);
        //console.log(data);

        if (data != null) {
            //리스트 저장
            gugunList.value = data;
        }
    } catch (error) {
        //console.log(error);
    }
    //시도에 따라서 리스트 다시 뽑기
    try {
        let loc = { sidoCode: sidoCode.value };

        //console.log(sidoCode.value);
        //관광지 리스트가 내려올 것'
        let { data } = await http.get(
            `attrs/sidolist?pageIdx=${page.value}&sidoCode=${sidoCode.value}`
        );
        //console.log(data);

        if (data != null) {
            //리스트 저장
            attrList.value = data;
        }
    } catch (error) {
        //console.log(error);
    }
};

const clickGugun = async () => {
    //시도 + 구군에 따라서 리스트 다시 뽑기
    try {
        //관광지 리스트가 내려올 것'
        let { data } = await http.get(
            `attrs/gugunlist?pageIdx=${page.value}&sidoCode=${sidoCode.value}&gugunCode=${gugunCode.value}`
        );

        if (data != null) {
            //리스트 저장
            attrList.value = data;
        }
    } catch (error) {
        //console.log(error);
    }
};

//좋아요 가져오기
const getLikeList = async () => {
    if (userStore.userId != undefined) {
        try {
            //관광지 리스트가 내려올 것'
            let { data } = await http.get(`likes/${userStore.userId}`);

            if (data != null) {
                //리스트 저장
                likeList.value = data;
            } else {
                likeList.value = [];
            }
        } catch (error) {
            //console.log(error);
        }
    }
};

const checkLike = (contentId) => {
    if (!Array.from(likeList.value).includes(contentId)) {
        insertLike(contentId);
    } else {
        deleteLike(contentId);
    }
};

//좋아요 찍기
const insertLike = async (contentId) => {
    try {
        //관광지 리스트가 내려올 것'
        let { data } = await http.post(`likes?userId=${userStore.userId}&contentId=${contentId}`);
    } catch (error) {
        //console.log(error);
    }
    getLikeList();
};
//좋아요 삭제
const deleteLike = async (contentId) => {
    try {
        //관광지 리스트가 내려올 것'
        let { data } = await http.delete(`likes?userId=${userStore.userId}&contentId=${contentId}`);
        //console.log(data);
    } catch (error) {
        //console.log(error);
    }
    getLikeList();
};

//먼저와야함
getLikeList();
getList();
getSidoList();
</script>

<style scoped></style>
