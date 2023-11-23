<script setup>
import { onMounted, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";

//Vue Material Kit 2 components
import MaterialButton from "@/components/MaterialButton.vue";
import MaterialInput from "@/components/MaterialInput.vue";
import MaterialTextArea from "@/components/MaterialTextArea.vue";

// image
import bgContact from "@/assets/img/examples/blog2.jpg";

// tooltip
import setTooltip from "@/assets/js/tooltip";
import http from "@/common/axios.js";
// store
import { useAppStore } from "@/stores";
import { useUserStore } from "@/stores/userStore";
const { userStore, setLogin } = useUserStore();
const store = useAppStore();
const editResidence = ref("");
const router = useRouter();

let select = ref([false, false, false, false]);
let selectImgUrl = ref([
    "@/assets/img/profile/profile1.png",
    "@/assets/img/profile/profile2.png",
    "@/assets/img/profile/profile3.png",
    "@/assets/img/profile/profile4.png",
]);
let curIdx = ref(userStore.userProfileImg - 1);
console.log(curIdx.value);
select.value[curIdx.value] = true;

onMounted(() => {
    setTooltip(store.bootstrap);
});
const changeToUpdate = async () => {
    console.log("update Page!!!!");

    let jsonDto = {
        residence: userStore.userResidence,
        phoneNum: userStore.userPhoneNum,
        userId: userStore.userId,
        selfIntro: userStore.userSelfIntro,
        profileImg: curIdx.value + 1,
    };

    console.log("jsonDto: ");
    console.log(jsonDto);
    try {
        let { data } = await http.put("/users/" + userStore.userId, jsonDto);
        userStore.userProfileImg = curIdx.value + 1;

        console.log("UpdateModalVue: data : ");
        console.log(data);
        alert("글이 수정되었습니다.");
        sessionStorage.setItem("userResidence", userStore.userResidence);
        sessionStorage.setItem("userPhoneNum", userStore.userPhoneNum);
        sessionStorage.setItem("userSelfIntro", userStore.userSelfIntro);
        sessionStorage.setItem("userProfileImg", userStore.userProfileImg);
    } catch (error) {
        console.log("InsertModalVue: error ");
        console.log(error);
    }

    //프로필 선택
};
const selectProfile = (idx) => {
    selectAll();
    curIdx.value = idx;
    select.value[idx] = true;
};

const selectAll = () => {
    select.value[0] = false;
    select.value[1] = false;
    select.value[2] = false;
    select.value[3] = false;
};

const deleteUser = async () => {
    try {
        let { data } = await http.delete("/users/" + userStore.userId);
        console.log(data);
        alert("탈퇴되었습니다.");
        sessionStorage.setItem("isLogin", false);
        sessionStorage.setItem("userEmail", "");

        userStore.isLogin = false;
    } catch (error) {
        console.log(error);
    }
};
</script>
<template>
    <section class="py-lg-5">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="card box-shadow-xl overflow-hidden mb-5">
                        <div class="row">
                            <div
                                class="col-lg-5 position-relative bg-cover px-0"
                                :style="{ backgroundImage: `url(${bgContact})` }"
                                loading="lazy"
                            >
                                <div
                                    class="z-index-2 text-center d-flex h-100 w-100 d-flex m-auto justify-content-center"
                                >
                                    <div class="mask bg-gradient-dark opacity-8"></div>
                                    <div
                                        class="p-5 ps-sm-8 position-relative text-start my-auto z-index-2"
                                    >
                                        <h3 class="text-white">Contact Info</h3>
                                        <p class="text-white opacity-8 mb-4">
                                            문의사항이 있으면 아래로 연락주세요
                                        </p>
                                        <p class="text-white opacity-8 mb-4"></p>
                                        <div class="d-flex p-2 text-white">
                                            <div>
                                                <i class="fas fa-phone text-sm"></i>
                                            </div>
                                            <div class="ps-3">
                                                <span class="text-sm opacity-8"
                                                    >(+40) 772 100 200</span
                                                >
                                            </div>
                                        </div>
                                        <div class="d-flex p-2 text-white">
                                            <div>
                                                <i class="fas fa-envelope text-sm"></i>
                                            </div>
                                            <div class="ps-3">
                                                <span class="text-sm opacity-8"
                                                    >hello@creative-tim.com</span
                                                >
                                            </div>
                                        </div>
                                        <div class="d-flex p-2 text-white">
                                            <div>
                                                <i class="fas fa-map-marker-alt text-sm"></i>
                                            </div>
                                            <div class="ps-3">
                                                <span class="text-sm opacity-8"
                                                    >Dyonisie Wolf Bucharest, RO 010458</span
                                                >
                                            </div>
                                        </div>
                                        <div class="mt-4">
                                            <MaterialButton
                                                color="none"
                                                size="lg"
                                                class="btn-icon-only btn-link text-white mb-0"
                                                data-toggle="tooltip"
                                                data-placement="bottom"
                                                data-original-title="Log in with Facebook"
                                            >
                                                <i class="fab fa-facebook"></i>
                                            </MaterialButton>
                                            <MaterialButton
                                                color="none"
                                                size="lg"
                                                class="btn-icon-only btn-link text-white mb-0"
                                                data-toggle="tooltip"
                                                data-placement="bottom"
                                                data-original-title="Log in with Twitter"
                                            >
                                                <i class="fab fa-twitter"></i>
                                            </MaterialButton>
                                            <MaterialButton
                                                color="none"
                                                size="lg"
                                                class="btn-icon-only btn-link text-white mb-0"
                                                data-toggle="tooltip"
                                                data-placement="bottom"
                                                data-original-title="Log in with Dribbble"
                                            >
                                                <i class="fab fa-dribbble"></i>
                                            </MaterialButton>
                                            <MaterialButton
                                                color="none"
                                                size="lg"
                                                class="btn-icon-only btn-link text-white mb-0"
                                                data-toggle="tooltip"
                                                data-placement="bottom"
                                                data-original-title="Log in with Instagram"
                                            >
                                                <i class="fab fa-instagram"></i>
                                            </MaterialButton>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-7">
                                <form class="p-3" id="contact-form" method="post">
                                    <div class="card-header px-4 py-sm-5 py-3">
                                        <h2>Change your personal Info</h2>
                                        <p class="lead">Please fill out the form.</p>
                                    </div>
                                    <div class="d-flex justify-content-around">
                                        <img
                                            src="@/assets/img/profile/profile1.png"
                                            alt="profile1"
                                            style="width: 50px; height: 50px"
                                            :style="{ opacity: select[0] ? 1 : 0.3 }"
                                            @click="selectProfile(0)"
                                        />

                                        &ensp;
                                        <img
                                            src="@/assets/img/profile/profile2.png"
                                            alt="profile1"
                                            style="width: 50px; height: 50px"
                                            :style="{ opacity: select[1] ? 1 : 0.5 }"
                                            @click="selectProfile(1)"
                                        />&ensp;
                                        <img
                                            src="@/assets/img/profile/profile3.png"
                                            alt="profile1"
                                            style="width: 50px; height: 50px"
                                            :style="{ opacity: select[2] ? 1 : 0.5 }"
                                            @click="selectProfile(2)"
                                        />&ensp;
                                        <img
                                            src="@/assets/img/profile/profile4.png"
                                            alt="profile1"
                                            style="width: 50px; height: 50px"
                                            :style="{ opacity: select[3] ? 1 : 0.5 }"
                                            @click="selectProfile(3)"
                                        />
                                    </div>

                                    <div class="card-body pt-1">
                                        <div class="row">
                                            <label for="ResidenceInsert" class="form-label"
                                                ><strong>거주지</strong></label
                                            >
                                            <div class="col-md-12 pe-2 mb-3">
                                                <input
                                                    class="form-control input-group-static mb-4"
                                                    style="background-color: rgb(234, 234, 234)"
                                                    v-model="userStore.userResidence"
                                                    type="text"
                                                    :placeholder="userStore.userResidence"
                                                />
                                            </div>
                                            <label for="PhoneNumberInsert" class="form-label"
                                                ><strong>PhoneNumber</strong></label
                                            >
                                            <div class="col-md-12 pe-2 mb-3">
                                                <input
                                                    class="form-control input-group-static mb-4"
                                                    style="background-color: rgb(234, 234, 234)"
                                                    label="PhoneNumber"
                                                    v-model="userStore.userPhoneNum"
                                                    type="text"
                                                    :placeholder="userStore.userPhoneNum"
                                                />
                                            </div>
                                            <label for="SelfIntroInsert" class="form-label"
                                                ><strong
                                                    >Change Your Self Introduction</strong
                                                ></label
                                            >

                                            <div class="col-md-12 pe-2 mb-3">
                                                <input
                                                    class="form-control input-group-static mb-4"
                                                    style="background-color: rgb(234, 234, 234)"
                                                    :placeholder="userStore.userSelfIntro"
                                                    v-model="userStore.userSelfIntro"
                                                    :rows="6"
                                                />
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6 text-end ms-auto">
                                                <RouterLink to="/">
                                                    <button
                                                        id="btnBoardInsert"
                                                        @click="deleteUser"
                                                        class="btn btn-sm btn-primary btn-outline float-end"
                                                        type="submit"
                                                    >
                                                        회원 탈퇴
                                                    </button>
                                                </RouterLink>
                                            </div>
                                            <div class="col-md-6 text-end ms-auto">
                                                <RouterLink :to="{ name: 'author' }">
                                                    <button
                                                        id="btnBoardInsert"
                                                        @click="changeToUpdate"
                                                        class="btn btn-sm btn-primary btn-outline float-end"
                                                        type="submit"
                                                    >
                                                        수정하기
                                                    </button></RouterLink
                                                >
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
