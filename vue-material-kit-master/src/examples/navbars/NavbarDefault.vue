<script setup>
import { RouterLink, useRouter } from "vue-router";
import { ref, watch } from "vue";
import { useWindowsWidth } from "../../assets/js/useWindowsWidth";

// images
import ArrDark from "@/assets/img/down-arrow-dark.svg";
import downArrow from "@/assets/img/down-arrow.svg";
import DownArrWhite from "@/assets/img/down-arrow-white.svg";

//login
import http from "@/common/axios.js";
import { useUserStore } from "@/stores/userStore";
const { userStore, setLogin, setRememberId } = useUserStore();
const router = useRouter();

const props = defineProps({
  // 로그인 로그아웃 때 쓰는데 로그아웃을 못하겠음
  action: {
    type: Object,
    route: String,
    color: String,
    label: String,
    default: () => ({
      route: "/pages/landing-pages/basic",
      color: "bg-gradient-success",
      label: "Login",
    }),
  },
  transparent: {
    type: Boolean,
    default: false,
  },
  light: {
    type: Boolean,
    default: false,
  },
  dark: {
    type: Boolean,
    default: false,
  },
  sticky: {
    type: Boolean,
    default: false,
  },
  darkText: {
    type: Boolean,
    default: false,
  },
});

// set arrow  color
function getArrowColor() {
  if (props.transparent && textDark.value) {
    return ArrDark;
  } else if (props.transparent) {
    return DownArrWhite;
  } else {
    return ArrDark;
  }
}

// set text color
const getTextColor = () => {
  let color;
  if (props.transparent && textDark.value) {
    color = "text-dark";
  } else if (props.transparent) {
    color = "text-white";
  } else {
    color = "text-dark";
  }

  return color;
};

// set nav color on mobile && desktop

let textDark = ref(props.darkText);
const { type } = useWindowsWidth();

if (type.value === "mobile") {
  textDark.value = true;
} else if (type.value === "desktop" && textDark.value == false) {
  textDark.value = false;
}

watch(
  () => type.value,
  (newValue) => {
    if (newValue === "mobile") {
      textDark.value = true;
    } else {
      textDark.value = false;
    }
  }
);

// 로그인 여부에 따라서 개인페이지 안보이기 보이기

const logout = async () => {
  try {
    let { data } = await http.get("/users/logout");

    if (data.result == "success") {
      //세션 스토리지에 담는다
      sessionStorage.removeItem("isLogin");
      sessionStorage.removeItem("userName");

      console.log(data);

      //store 변경
      setLogin({
        isLogin: false,
        userName: "",
        userEmail: "",
      });

      if (userStore.isRememberId) {
        setRememberId({
          isRememberId: true,
          userEmail: sessionStorage.getItem("userEmail"),
        });
      } else {
        setRememberId({
          isRememberId: false,
          userEmail: "",
        });
      }

      router.push("/");
    } else if (data.result == "fail") {
      alert("이메일 또는 비밀번호를 확인해주세요");
    }
  } catch (error) {
    console.log(error);
  }
};
</script>
<template>
  <nav
    class="navbar navbar-expand-lg top-0"
    :class="{
      'z-index-3 w-100 shadow-none navbar-transparent position-absolute my-3': props.transparent,
      'my-3 blur border-radius-lg z-index-3 py-2 shadow py-2 start-0 end-0 mx-4 position-absolute mt-4': true,
      'navbar-light bg-white py-3': props.light,
      ' navbar-dark bg-gradient-dark z-index-3 py-3': props.dark,
    }"
  >
    <!-- <nav
        class="navbar navbar-expand-lg top-0"
        :class="{
            'z-index-3 w-100 shadow-none navbar-transparent position-absolute my-3':
                props.transparent,
            'my-3 blur border-radius-lg z-index-3 py-2 shadow py-2 start-0 end-0 mx-4 position-absolute mt-4':
                props.sticky,
            'navbar-light bg-white py-3': props.light,
            ' navbar-dark bg-gradient-dark z-index-3 py-3': props.dark,
        }"
    > -->
    <div
      :class="props.transparent || props.light || props.dark ? 'container' : 'container-fluid px-0'"
    >
      <RouterLink
        class="navbar-brand d-none d-md-block"
        :class="[
          (props.transparent && textDark.value) || !props.transparent
            ? 'text-dark font-weight-bolder ms-sm-3'
            : 'text-white font-weight-bolder ms-sm-3',
        ]"
        :to="{ name: 'presentation' }"
        rel="tooltip"
        title="Voyage Hub"
        data-placement="bottom"
      >
        <img src="@/assets/img/icon.png" style="height: 20px; width: 20px" alt="icon" />&ensp;Voyage
        Hub
      </RouterLink>
      <RouterLink
        class="navbar-brand d-block d-md-none"
        :class="props.transparent || props.dark ? 'text-white' : 'font-weight-bolder ms-sm-3'"
        to="/"
        rel="tooltip"
        title="Voyage Hub"
        data-placement="bottom"
      >
        Voyage Hub
      </RouterLink>
      <div v-show="!userStore.isLogin">
        <router-link
          to="/pages/landing-pages/basic"
          class="nav-item btn btn-sm bg-gradient-success mb-0 ms-auto d-lg-none d-block"
          :class="action.color"
          >Login</router-link
        >
      </div>

      <div v-show="userStore.isLogin">
        <a
          href="#"
          @click="logout"
          class="nav-item btn btn-sm bg-gradient-success mb-0 ms-auto d-lg-none d-block"
          :class="action.color"
          >Logout</a
        >
      </div>
      <button
        class="navbar-toggler shadow-none ms-2"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navigation"
        aria-controls="navigation"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon mt-2">
          <span class="navbar-toggler-bar bar1"></span>
          <span class="navbar-toggler-bar bar2"></span>
          <span class="navbar-toggler-bar bar3"></span>
        </span>
      </button>
      <div class="collapse navbar-collapse w-100 pt-3 pb-2 py-lg-0" id="navigation">
        <ul class="navbar-nav navbar-nav-hover ms-auto">
          <li class="nav-item dropdown dropdown-hover mx-2">
            <a
              role="button"
              class="nav-link ps-2 d-flex cursor-pointer align-items-center"
              :class="getTextColor()"
              id="dropdownMenuPages"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="material-icons opacity-6 me-2 text-md" :class="getTextColor()">dashboard</i>
              Menu
              <img :src="getArrowColor()" alt="down-arrow" class="arrow ms-2 d-lg-block d-none" />
              <img
                :src="getArrowColor()"
                alt="down-arrow"
                class="arrow ms-1 d-lg-none d-block ms-auto"
              />
            </a>
            <div
              class="dropdown-menu dropdown-menu-animation ms-n3 dropdown-md p-3 border-radius-xl mt-0 mt-lg-3"
              aria-labelledby="dropdownMenuPages"
            >
              <div class="row d-none d-lg-block">
                <div class="col-12 px-4 py-2">
                  <div class="row">
                    <div class="position-relative">
                      <div
                        class="dropdown-header text-dark font-weight-bolder d-flex align-items-center px-1"
                      >
                        전체 회원 이용 기능
                      </div>
                      <RouterLink :to="{ name: 'about' }" class="dropdown-item border-radius-md">
                        <span>게시판</span>
                      </RouterLink>

                      <!-- 로그인했을 때만 보이게-->
                      <div
                        class="dropdown-header text-dark font-weight-bolder d-flex align-items-center px-1"
                        v-show="userStore.isLogin"
                      >
                        회원 전용 기능 (로그인해야 보입니다)
                      </div>
                      <RouterLink
                        :to="{ name: 'author' }"
                        class="dropdown-item border-radius-md"
                        v-show="userStore.isLogin"
                      >
                        <span>개인 페이지</span>
                      </RouterLink>
                    </div>
                  </div>
                </div>
              </div>
              <div class="d-lg-none">
                <div
                  class="dropdown-header text-dark font-weight-bolder d-flex align-items-center px-0"
                >
                  페이지 이동
                </div>
                <RouterLink :to="{ name: 'about' }" class="dropdown-item border-radius-md">
                  <span>게시판</span>
                </RouterLink>

                <RouterLink :to="{ name: 'author' }" class="dropdown-item border-radius-md">
                  <span>개인 페이지</span>
                </RouterLink>
              </div>
            </div>
          </li>
        </ul>
        <!-- 로그인 버튼-->
        <ul class="navbar-nav d-lg-block d-none">
          <li class="nav-item" v-show="!userStore.isLogin">
            <router-link
              to="/pages/landing-pages/basic"
              class="btn btn-sm mb-0"
              :class="action.color"
              >Login</router-link
            >
          </li>
          <li class="nav-item" v-show="userStore.isLogin">
            <button class="btn btn-sm mb-0" :class="action.color" href="#" @click="logout">
              Logout
            </button>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
</template>
