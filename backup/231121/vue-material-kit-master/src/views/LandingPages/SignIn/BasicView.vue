<script setup>
import { onMounted, ref } from "vue";

// 로그인 뷰

// example components
import Header from "@/examples/Header.vue";

//Vue Material Kit 2 components
import MaterialButton from "@/components/MaterialButton.vue";

// 로그인
import http from "@/common/axios.js";
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";

// material-input
import setMaterialInput from "@/assets/js/material-input";

onMounted(() => {
    setMaterialInput();
});
const { userStore, setLogin, setRememberId } = useUserStore();
const router = useRouter();

const init = () => {
    let isRememberId = sessionStorage.getItem("isRememberId");

    if (isRememberId) {
        let userEmail = sessionStorage.getItem("userEmail");

        setRememberId({
            isRememberId: isRememberId,
            userEmail: userEmail,
        });
    }
};

const login = async () => {
    let loginObj = {
        email: userStore.userEmail,
        password: userStore.userPassword,
    };
    try {
        let { data } = await http.post("/users/login", loginObj);
        console.log(loginObj);

        if (data.result == "success") {
            //세션 스토리지에 담는다
            sessionStorage.setItem("isRememberId", userStore.isRememberId);
            sessionStorage.setItem("isLogin", true);
            sessionStorage.setItem("userEmail", data.userEmail);
            sessionStorage.setItem("userId", data.userId);
            sessionStorage.setItem("userName", data.userName);
            // sessionStorage.setItem("userProfileImageUrl", data.userProfileImageUrl);
            console.log(data);

            //store 변경
            setLogin({
                isLogin: true,
                userName: data.userName,
                userEmail: data.userEmail,
                userId: data.userId,
                // userProfileImageUrl: data.userProfileImageUrl,
            });
            setRememberId({
                isRememberId: userStore.isRememberId,
                userEmail: data.userEmail,
            });

            router.push("/");
        } else if (data.result == "fail") {
            alert("이메일 또는 비밀번호를 확인해주세요");
        }
    } catch (error) {
        console.log(error);
    }
};

init();
</script>
<template>
    <!-- <DefaultNavbar transparent /> -->
    <Header>
        <div
            class="page-header align-items-start min-vh-100"
            :style="{
                backgroundImage:
                    'url(https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80)',
            }"
            loading="lazy"
        >
            <span class="mask bg-gradient-dark opacity-6"></span>
            <div class="container my-auto">
                <div class="row">
                    <div class="col-lg-4 col-md-8 col-12 mx-auto">
                        <div class="card z-index-0 fadeIn3 fadeInBottom">
                            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                                <div
                                    class="bg-gradient-success shadow-success border-radius-lg py-3 pe-1"
                                >
                                    <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">
                                        Sign in
                                    </h4>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="text-start">
                                    <!-- <MaterialInput
                                        id="email"
                                        class="input-group-outline my-3"
                                        v-model="userEmail"
                                        placeholder="email"
                                        type="email"
                                    />
                                    <MaterialInput
                                        id="password"
                                        class="input-group-outline mb-3"
                                        v-model="userPassword"
                                        placeholder="password"
                                        type="password"
                                    /> -->
                                    <input
                                        id="email"
                                        class="form-control input my-3"
                                        v-model="userStore.userEmail"
                                        style="background-color: rgb(234, 234, 234)"
                                        placeholder="email"
                                        type="email"
                                    />
                                    <input
                                        id="password"
                                        class="form-control input mb-3"
                                        v-model="userStore.userPassword"
                                        placeholder="password"
                                        style="background-color: rgb(234, 234, 234)"
                                        type="password"
                                    />
                                    <div class="form-check form-switch">
                                        <input
                                            class="form-check-input d-flex align-items-center mb-3"
                                            type="checkbox"
                                            v-model="userStore.isRememberId"
                                        />
                                        <label class="form-check-label" for="flexSwitchCheckDefault"
                                            >rememberMe</label
                                        >
                                    </div>
                                    <!-- <MaterialSwitch
                                        class="d-flex align-items-center mb-3"
                                        id="rememberMe"
                                        labelClass="mb-0 ms-3"
                                        v-model="userStore.isRememberId"
                                        checked
                                        >Remember me</MaterialSwitch
                                    > -->

                                    <div class="text-center">
                                        <MaterialButton
                                            @click="login"
                                            class="my-4 mb-2"
                                            variant="gradient"
                                            color="success"
                                            fullWidth
                                            >Sign in</MaterialButton
                                        >
                                    </div>
                                    <p class="mt-4 text-sm text-center">
                                        Don't have an account?
                                        <router-link
                                            to="/pages/landing-pages/contact-us"
                                            class="text-success text-gradient font-weight-bold"
                                            >Sign up</router-link
                                        >
                                        <!-- <a
                      href="/pages/landing-pages/contact-us"
                      class="text-success text-gradient font-weight-bold"
                      >Sign up</a
                    > -->
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="footer position-absolute bottom-2 py-2 w-100">
                <div class="container">
                    <div class="row align-items-center justify-content-lg-between">
                        <div class="col-12 col-md-6 my-auto">
                            <div class="copyright text-center text-sm text-white text-lg-start">
                                © {{ new Date().getFullYear() }}, made with
                                <i class="fa fa-heart" aria-hidden="true"></i> by
                                <a
                                    href="https://www.creative-tim.com"
                                    class="font-weight-bold text-white"
                                    target="_blank"
                                    >Creative Tim</a
                                >
                                for a better web.
                            </div>
                        </div>
                        <div class="col-12 col-md-6">
                            <ul
                                class="nav nav-footer justify-content-center justify-content-lg-end"
                            >
                                <li class="nav-item">
                                    <a
                                        href="https://www.creative-tim.com"
                                        class="nav-link text-white"
                                        target="_blank"
                                        >Creative Tim</a
                                    >
                                </li>
                                <li class="nav-item">
                                    <a
                                        href="https://www.creative-tim.com/presentation"
                                        class="nav-link text-white"
                                        target="_blank"
                                        >About Us</a
                                    >
                                </li>
                                <li class="nav-item">
                                    <a
                                        href="https://www.creative-tim.com/blog"
                                        class="nav-link text-white"
                                        target="_blank"
                                        >Blog</a
                                    >
                                </li>
                                <li class="nav-item">
                                    <a
                                        href="https://www.creative-tim.com/license"
                                        class="nav-link pe-0 text-white"
                                        target="_blank"
                                        >License</a
                                    >
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </Header>
</template>
