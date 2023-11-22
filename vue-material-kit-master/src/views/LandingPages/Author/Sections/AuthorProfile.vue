<script setup>
import { onMounted } from "vue";

//Vue Material Kit 2 components
import MaterialAvatar from "@/components/MaterialAvatar.vue";
import MaterialButton from "@/components/MaterialButton.vue";

// image
import profile1 from "@/assets/img/profile/profile1.png";
import profile2 from "@/assets/img/profile/profile2.png";
import profile3 from "@/assets/img/profile/profile3.png";
import profile4 from "@/assets/img/profile/profile4.png";
import http from "@/common/axios.js";

// material-input
import setMaterialInput from "@/assets/js/material-input";
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore";
const { userStore, setLogin, setRememberId } = useUserStore();
const profile = [profile1, profile2, profile3, profile4];

const profileInit = () => {
    userStore.userProfileImg == undefined ? 0 : userStore.userProfileImg;
};

onMounted(() => {
    setMaterialInput();
});
// const getUserInfo = async () => {
//   try {
//     let { data } = await http.get("/users/" + sessionStorage.getItem("userId"));
//     console.log("UsersPage: data : ");
//     console.log(data);

//     setLogin(data);
//   } catch (error) {
//     console.log("UsersPage: error : ");
//     console.log(error);
//   }
// };
// getUserInfo();
</script>
<template>
    <section class="py-sm-7 py-5 position-relative">
        <div class="container">
            <div class="row">
                <div class="col-12 mx-auto">
                    <div class="mt-n8 mt-md-n9 text-center">
                        <div class="blur-shadow-avatar">
                            <MaterialAvatar
                                size="xxl"
                                class="shadow-xl position-relative z-index-2"
                                :image="profile[userStore.userProfileImg - 1]"
                                alt="Avatar"
                            />
                        </div>
                    </div>
                    <!-- 개인 페이지의 나의 프로필!!! -->
                    <div class="row py-7">
                        <div
                            class="col-lg-7 col-md-7 z-index-2 position-relative px-md-2 px-sm-5 mx-auto"
                        >
                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <h3 class="mb-0">{{ userStore.userName }}</h3>
                                <!-- <div class="d-block">
                  <MaterialButton
                    class="text-nowrap mb-0"
                    variant="outline"
                    color="success"
                    size="sm"
                    >Follow</MaterialButton
                  >
                </div> -->
                            </div>
                            <br />
                            <div class="row mb-4">
                                <div class="col-auto">
                                    <span class="h6 me-1">거주지</span>
                                    <span>{{ userStore.userResidence }}</span>
                                </div>
                                <div class="col-auto">
                                    <span class="h6 me-1">전화번호</span>
                                    <span>{{ userStore.userPhoneNum }}</span>
                                </div>
                                <div class="col-auto">
                                    <span class="h6 me-1">이메일</span>
                                    <span>{{ userStore.userEmail }}</span>
                                </div>
                            </div>
                            <span class="h6 me-1">자기소개</span>
                            <p class="text-lg mb-0">
                                {{ userStore.userSelfIntro }}
                                <br />
                                <router-link :to="{ name: 'authorcontact' }">
                                    <span class="float-end text-success icon-move-right"
                                        >개인정보 수정하기
                                        <i class="fas fa-arrow-right text-sm ms-1"></i>
                                    </span>
                                </router-link>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
