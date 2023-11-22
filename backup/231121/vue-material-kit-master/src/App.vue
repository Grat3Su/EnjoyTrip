<script setup>
/*
=========================================================
* Vue Material Kit 2 - v2.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/vue-material-kit
* Copyright 2022 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/
import { RouterView } from "vue-router";
import DefaultNavbar from "@/examples/navbars/NavbarDefault.vue";
import { useUserStore } from "@/stores/userStore";

const { userStore, setLogin, setRememberId } = useUserStore();
let isLogin = sessionStorage.getItem("isLogin");
let isRememberId = sessionStorage.getItem("isRememberId");

if (isLogin == "true") {
    let userEmail = sessionStorage.getItem("userEmail");
    let userName = sessionStorage.getItem("userName");
    let userId = sessionStorage.getItem("userId");
    setLogin({
        isLogin: true,
        userName: userName,
        userEmail: userEmail,
        userId: userId,
        userPassword: "",
    });
} else if (isRememberId == "true") {
    let userEmail = sessionStorage.getItem("userEmail");
    setRememberId({
        isRememberId: true,
        userEmail: userEmail,
    });
}
</script>

<template>
    <DefaultNavbar />
    <!-- <DefaultNavbar
        :sticky="true"
        :action="{
            route: 'https://www.creative-tim.com/product/vue-material-kit-pro',
            color: 'bg-gradient-success',
            label: 'Login',
        }"
    /> -->
    <router-view />
</template>
