<script setup>
import { onMounted, onUnmounted } from "vue";
import { RouterLink, useRouter } from "vue-router";

//example components
// import DefaultNavbar from "../../../examples/navbars/NavbarDefault.vue";
import DefaultFooter from "../../../examples/footers/FooterDefault.vue";

//image
import bg0 from "@/assets/img/bg9.jpg";

//dep
import Typed from "typed.js";

// pagination
import PaginationUI from "@/components/PaginationUI.vue";
import util from "@/common/util.js";

// store
import { useBoardStore } from "@/stores/boardStore";

//login 관리
import { useUserStore } from "@/stores/userStore";
const { userStore, setLogin, setRememberId } = useUserStore();

const { boardStore, boardList, setBoardMovePage } = useBoardStore();

const body = document.getElementsByTagName("body")[0];

//hooks
onMounted(() => {
  body.classList.add("about-us");
  body.classList.add("bg-gray-200");
  boardStore.searchWord = "";
  boardList();

  if (document.getElementById("typed")) {
    // eslint-disable-next-line no-unused-vars
    var typed = new Typed("#typed", {
      stringsElement: "#typed-strings",
      typeSpeed: 90,
      backSpeed: 90,
      backDelay: 200,
      startDelay: 500,
      loop: true,
    });
  }
});

onUnmounted(() => {
  body.classList.remove("about-us");
  body.classList.remove("bg-gray-200");
});
console.log("list page!!!!");
boardList();
console.log(boardStore.list);
const router = useRouter();
// 게시판 상세보기 눌렀을 때 호출하는 함수 detail
const detail = async (boardId) => {
  try {
    router.push({
      name: "detailboard",
      params: { boardId: boardId },
    });
  } catch (error) {
    console.log("BoardMainVue: error : ");
    console.log(error);
  }
};

// pagination
const movePage = (pageIndex) => {
  console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
  setBoardMovePage(pageIndex);
  boardList();
};
</script>

<template>
  <!-- <DefaultNavbar
        :action="{
            route: 'javascript:;',
            label: 'Buy Now',
            color: 'btn-white',
        }"
        transparent
    /> -->
  <header class="bg-gradient-dark">
    <div class="page-header min-vh-75" :style="{ backgroundImage: `url(${bg0})` }">
      <span class="mask bg-gradient-dark opacity-6"></span>
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-8 text-center mx-auto my-auto">
            <h1 class="text-white">
              한국 최고의 여행지는 <span class="text-white" id="typed"></span>
            </h1>
            <div id="typed-strings">
              <h1>제주도</h1>
              <h1>부산</h1>
              <h1>서울</h1>
            </div>
            <p class="lead mb-4 text-white opacity-8">
              한국 최고의 여행지를 추천해주세요<br />
              여행지 후기를 서로 공유해봐요!
            </p>
          </div>
        </div>
      </div>
    </div>
  </header>
  <div class="card card-body shadow-xl mx-3 mx-md-4 mt-n6">
    <div class="container-xxl py-5">
      <div class="container">
        <div class="text-center mx-auto mb-5 wow" style="max-width: 900px">
          <!-- <div class="container mt-3"> -->
          <!-- mt-3 추가 -->

          <h6 class="text-primary">게시판</h6>
          <h1 class="mb-4">What Our Clients Say!</h1>

          <div class="input-group mb-3 mt-3">
            <!-- mb-3 mt-3  추가 -->
            <input
              id="inputSearchWord"
              type="text"
              class="form-control"
              v-model="boardStore.searchWord"
              @keydown.enter="boardList"
              placeholder="검색어를 입력하세요"
            />
            <button @click="boardList" id="btnSearchWord" class="btn btn-success" type="button">
              Search
            </button>
          </div>

          <table class="table table-hover">
            <thead>
              <tr>
                <th>#</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일시</th>
              </tr>
            </thead>
            <tbody id="boardTbody">
              <tr
                v-for="board in boardStore.list"
                :key="board.boardId"
                @click="detail(board.boardId)"
              >
                <td>{{ board.boardId }}</td>
                <td>{{ board.boardTitle }}</td>
                <td>{{ board.userName }}</td>
                <td>{{ util.makeDateStr(board.boardTime.date, "/") }}</td>
              </tr>
            </tbody>
          </table>

          <PaginationUI v-on:call-parent="movePage"></PaginationUI>

          <RouterLink
            :to="{ name: 'insertboard' }"
            tag="button"
            class="btn btn-sm btn-primary"
            id="btnBoardInsertUI"
            style="float: right"
            v-show="userStore.isLogin"
          >
            글쓰기
          </RouterLink>
        </div>
      </div>
    </div>
  </div>
  <DefaultFooter />
</template>

<style>
.page-link.active,
.active > .page-link {
  z-index: 3;
  color: greenyellow !important;
  background-color: teal !important;
  border-color: teal !important;
}
</style>
