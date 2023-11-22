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
  setLogin({
    isLogin: true,
    userName: sessionStorage.getItem("userName"),
    userEmail: sessionStorage.getItem("userEmail"),
    userPassword: "",
    userPhoneNum: sessionStorage.getItem("userPhoneNum"),
    userResidence: sessionStorage.getItem("userResidence"),
    userSelfIntro: sessionStorage.getItem("userSelfIntro"),
    isRememberId: sessionStorage.getItem("isRememberId"),
    isLogin: sessionStorage.getItem("isLogin"),
    userId: sessionStorage.getItem("userId"),
    userProfileImg: sessionStorage.getItem("userProfileImg"),
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
  <link rel="shortcut icon" href="#" />
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
