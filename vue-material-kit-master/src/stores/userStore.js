import { reactive } from "vue";
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
    userSelfIntro: "",
    userResidence: "",
    userPhoneNum: "",
    userProfileImg: "",
  });

  //login 후 변경
  const setLogin = (payload) => {
    console.log(payload);
    userStore.isLogin = payload.isLogin;
    userStore.userName = payload.userName;
    userStore.userEmail = payload.userEmail;
    userStore.userId = payload.userId;
    userStore.userPassword = "";
    userStore.userSelfIntro = payload.userSelfIntro;
    userStore.userResidence = payload.userResidence;
    userStore.userPhoneNum = payload.userPhoneNum;
    userStore.userProfileImg = payload.userProfileImg;
    // userStore.userProfileImageUrl = payload.userProfileImageUrl;
    console.log("userStore:");
    console.log(userStore);
  };
  const setRememberId = (payload) => {
    console.log("isremember : " + userStore.isRememberId);
    userStore.isRememberId = payload.isRememberId;
    userStore.userEmail = payload.userEmail;
  };

  return { userStore, setLogin, setRememberId };
});
