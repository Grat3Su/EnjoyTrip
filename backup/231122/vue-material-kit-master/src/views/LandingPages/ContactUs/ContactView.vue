<script setup>
import { onMounted } from "vue";

//example components
import DefaultNavbar from "@/examples/navbars/NavbarDefault.vue";
import DefaultFooter from "@/examples/footers/FooterDefault.vue";

//image
import image from "@/assets/img/illustrations/illustration-signin.jpg";

//material components
import MaterialButton from "@/components/MaterialButton.vue";
// material-input
import setMaterialInput from "@/assets/js/material-input";

//signup
import http from "@/common/axios.js";
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";

import { ref } from "vue";

const { userStore, setLogin, setRememberId } = useUserStore();
const router = useRouter();

onMounted(() => {
    setMaterialInput();
});

let userEmail = ref("");
let userPassword = ref("");
let userName = ref("");

let userSelfIntro = ref("");
let userResidence = ref("");
let userPhoneNum = ref("");

let select = ref([true, false, false, false]);
let selectImgUrl = ref([
    "@/assets/img/profile/profile1.png",
    "@/assets/img/profile/profile2.png",
    "@/assets/img/profile/profile3.png",
    "@/assets/img/profile/profile4.png",
]);
let curIdx = ref(0);

//전송
const regist = async () => {
    let userObj = {
        userId: 0,
        email: userEmail.value,
        password: userPassword.value,
        name: userName.value,
        phoneNum: userPhoneNum.value,
        selfIntro: userSelfIntro.value,
        residence: userResidence.value,

        profileImg: curIdx.value + 1,
    };

    try {
        console.log(userObj);
        let { data } = await http.post("/users", userObj);
        console.log(data);

        if (data == 1) {
            alert("가입에 성공했습니다.");
            router.push("/pages/landing-pages/basic");
        } else {
            alert("가입에 실패했습니다.");
        }
    } catch (error) {
        console.log(error);
    }
};

//프로필 선택
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
</script>
<template>
    <!-- <div class="container position-sticky z-index-sticky top-0">
        <div class="row">
            <div class="col-12">
                <DefaultNavbar
                    :sticky="true"
                    :action="{
                        route: 'https://www.creative-tim.com/product/vue-material-kit-pro',
                        color: 'bg-gradient-success',
                        label: 'Login',
                    }"
                />
            </div>
        </div>
    </div> -->
    <section>
        <div class="page-header min-vh-100">
            <div class="container">
                <div class="row">
                    <div
                        class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column"
                    >
                        <div
                            class="position-relative h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center"
                            :style="{
                                backgroundImage: `url(${image})`,
                                backgroundSize: 'cover',
                            }"
                            loading="lazy"
                        ></div>
                    </div>
                    <div
                        class="mt-8 col-xl-5 col-lg-6 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5"
                    >
                        <div
                            class="card d-flex blur justify-content-center shadow-lg my-sm-0 my-sm-6 mt-8 mb-5"
                        >
                            <div
                                class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent"
                            >
                                <div
                                    class="bg-gradient-success shadow-success border-radius-lg p-3"
                                >
                                    <h3 class="text-white text-success mb-0">회원가입</h3>
                                </div>
                            </div>
                            <div class="card-body">
                                <p class="pb-3">회원가입을 위한 정보를 입력해주세요.</p>
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

                                <div class="card-body p-0 my-3">
                                    <!-- 이름 -->
                                    <div class="col-md-12">
                                        <input
                                            class="form-control mb-4"
                                            id="userName"
                                            placeholder="Full Name"
                                            v-model="userName"
                                            style="background-color: rgb(234, 234, 234)"
                                            type="text"
                                        />
                                    </div>
                                    <!-- 이메일 -->
                                    <div class="row">
                                        <div class="col-md-6 ps-md-2">
                                            <input
                                                class="form-control input mb-4"
                                                id="userEmail"
                                                placeholder="Email"
                                                v-model="userEmail"
                                                style="background-color: rgb(234, 234, 234)"
                                                type="email"
                                            />
                                        </div>
                                        <!-- 비번 -->
                                        <div class="col-md-6 ps-md-2">
                                            <input
                                                class="form-control mb-4"
                                                id="userPassword"
                                                placeholder="password"
                                                v-model="userPassword"
                                                style="background-color: rgb(234, 234, 234)"
                                                type="password"
                                            />
                                        </div>
                                        <!-- 전화번호 -->
                                        <div class="col-md-6 ps-md-2">
                                            <input
                                                class="form-control mb-4"
                                                id="userPhoneNum"
                                                placeholder="Phone Number"
                                                v-model="userPhoneNum"
                                                style="background-color: rgb(234, 234, 234)"
                                                type="text"
                                            />
                                        </div>
                                        <!-- 주소 -->
                                        <div class="col-md-6 ps-md-2">
                                            <input
                                                class="form-control mb-4"
                                                id="userResidence"
                                                placeholder="거주지"
                                                v-model="userResidence"
                                                style="background-color: rgb(234, 234, 234)"
                                                type="text"
                                            />
                                        </div>
                                    </div>
                                    <!-- 자기소개 -->
                                    <div class="col-md-12">
                                        <input
                                            class="form-control mb-4"
                                            id="introduce"
                                            placeholder="introduce"
                                            v-model="userSelfIntro"
                                            style="background-color: rgb(234, 234, 234)"
                                            type="text"
                                        />
                                    </div>
                                </div>
                                <div class="col-md-12 text-center">
                                    <MaterialButton
                                        @click="regist"
                                        variant="gradient"
                                        color="success"
                                        class="mt-3 mb-0"
                                        >회원가입</MaterialButton
                                    >
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <DefaultFooter />
</template>
