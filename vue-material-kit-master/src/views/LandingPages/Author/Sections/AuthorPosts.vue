<script setup>
// example components
import { ref } from "vue";
import http from "@/common/axios.js";
import { useUserStore } from "@/stores/userStore";
import noimg from "@/assets/img/noImage.png";

const { userStore } = useUserStore();
const likeList = ref([]);
// Reactive data
const openCollapseId = ref(null);
//좋아요 가져오기
const getLikeList = async () => {
    try {
        //관광지 리스트가 내려올 것'
        let { data } = await http.get(`likes/detail/${userStore.userId}`);

        if (data != null) {
            //리스트 저장
            likeList.value = data;
        } else {
            likeList.value = [];
        }

        openCollapseId.value = null;
        console.log("getLikeList in SUCCESS!!");
        console.log("get Data:");
        console.log(data);
    } catch (error) {
        console.log("error!!!");
        console.log(error);
    }
};

getLikeList();
console.log("likeList: ");
console.log(likeList);

// Function to toggle collapse
const toggleCollapse = (contentId) => {
    openCollapseId.value = openCollapseId.value === contentId ? null : contentId;
};

// Function to check if collapse is open
const isOpen = (contentId) => openCollapseId.value === contentId;

const deleteHeart = async (contentId) => {
    try {
        //관광지 리스트가 내려올 것'
        let { data } = await http.delete(`likes?userId=${userStore.userId}&contentId=${contentId}`);
        //console.log(data);
    } catch (error) {
        //console.log(error);
    }
    getLikeList();
};

// Function to check if collapse is expanded
const isExpanded = (contentId) => (isOpen(contentId) ? "true" : "false");
</script>
<template>
    <section class="py-3">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <h2 class="mb-5">Check My Favorite Attractions!</h2>
                </div>
            </div>
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
                                fill="red"
                                class="bi bi-heart-fill d-flex"
                                viewBox="0 0 16 16"
                                @click="deleteHeart(like.contentId)"
                            >
                                <path
                                    fill-rule="evenodd"
                                    d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                                />
                            </svg>
                        </div>
                        <p class="card-text mb-6">{{ like.addr1 }} {{ like.addr2 }}</p>
                        <div class="card-text d-flex align-items-center position-absolute bottom-3">
                            <button
                                class="btn btn-warning"
                                type="button"
                                data-bs-toggle="collapse"
                                :data-bs-target="'#collapseExample' + like.contentId"
                                aria-expanded="false"
                                aria-controls="collapseExample"
                            >
                                {{ like.title }} 상세 설명 보기
                            </button>
                        </div>
                        <div class="collapse" :id="'collapseExample' + like.contentId">
                            <div class="card card-text">
                                {{ like.overview }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
