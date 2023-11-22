<script setup>
import { onMounted, onUnmounted, ref } from "vue";
import { RouterLink } from "vue-router";

//example components
// import DefaultNavbar from "../../../examples/navbars/NavbarDefault.vue";
import DefaultFooter from "../../../examples/footers/FooterDefault.vue";

//image
import bg0 from "@/assets/img/bg9.jpg";

//dep
import Typed from "typed.js";

import http from "@/common/axios.js";
import CKEditor from "@ckeditor/ckeditor5-vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { useUserStore } from "@/stores/userStore";
const { userStore, setLogin, setRememberId } = useUserStore();

const body = document.getElementsByTagName("body")[0];
//hooks
onMounted(() => {
  body.classList.add("about-us");
  body.classList.add("bg-gray-200");

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

// 내가 입력
const ckeditor = CKEditor.component;
const editor = ClassicEditor;
const editorData = ref("");
const editorConfig = {};
const boardTitle = ref("");

const initUI = () => {
  boardTitle.value = "";
  editorData.value = ""; // editorData가 쉽게 초기화됨
};

const boardInsert = async () => {
  // let formData = new FormData();
  // formData.append("boardTitle", boardTitle.value);
  // formData.append("content", editorData.value);
  console.log("insert Page!!!!");

  let jsonDto = {
    boardTitle: boardTitle.value,
    boardDetail: editorData.value,
    userId: userStore.userId,
    userName: userStore.userName,
  };

  try {
    let { data } = await http.post("/boards", jsonDto);

    console.log("InsertModalVue: data : ");
    console.log(data);
    // alert("글이 등록되었습니다.");
    boardList();
    // if (data.result == "login") {
    //   doLogout();
    // } else {
    //   alert("글이 등록되었습니다.");
    //   closeModal();
    // }
  } catch (error) {
    console.log("InsertModalVue: error ");
    console.log(error);
  }
};
</script>

<template>
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
    <h5 class="modal-title">글 쓰기</h5>
    <div class="mb-3">
      <label for="titleInsert" class="form-label"><strong>제목</strong></label>
      <input
        v-model="boardTitle"
        type="text"
        class="form-control"
        placeholder="제목을 입력해주세요."
      />
    </div>
    <div class="mb-3">
      <label for="contentInsert" class="form-label"><strong>내용</strong></label>
      <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
    </div>
    <RouterLink :to="{ name: 'about' }">
      <button
        id="btnBoardInsert"
        @click="boardInsert"
        class="btn btn-sm btn-primary btn-outline float-end"
        type="submit"
      >
        등록
      </button>
    </RouterLink>
  </div>
  <DefaultFooter />
</template>
