import { ref, reactive } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore("userStore", () => {
    const userStore = reactive({
        isLogin: false, //유저 로그인여부
        isRememberId: false, //

        userName: "",
        // userProfileImageUrl: notLoginProfileImageUrl,
        userId: 0,
        userEmail: "",
        userPassword: "",
    });

    //login 후 변경
    const setLogin = (payload) => {
        console.log(payload);
        userStore.isLogin = payload.isLogin;
        userStore.userName = payload.userName;
        userStore.userEmail = payload.userEmail;
        userStore.userId = payload.userId;
        userStore.userPassword = "";
        // userStore.userProfileImageUrl = payload.userProfileImageUrl;
        console.log(userStore);
    };
    const setRememberId = (payload) => {
        console.log("iseemeber : " + userStore.isRememberId);
        userStore.isRememberId = payload.isRememberId;
        userStore.userEmail = payload.userEmail;
    };

    return { userStore, setLogin, setRememberId };
});
