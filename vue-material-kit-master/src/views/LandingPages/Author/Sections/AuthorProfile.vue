<script setup>
import { onMounted } from "vue";

//Vue Material Kit 2 components
import MaterialAvatar from "@/components/MaterialAvatar.vue";
import MaterialButton from "@/components/MaterialButton.vue";

// image
import profilePic from "@/assets/img/bruce-mars.jpg";

// material-input
import setMaterialInput from "@/assets/js/material-input";
onMounted(() => {
  setMaterialInput();
});
</script>
<template>
  <section class="py-sm-7 py-5 position-relative">
    <div class="container">
      <div class="row">
        <div class="col-12 mx-auto">
          <div class="mt-n8 mt-md-n9 text-center">
            <div class="blur-shadow-avatar">
              <MaterialAvatar
                size="xxl"
                class="shadow-xl position-relative z-index-2"
                :image="profilePic"
                alt="Avatar"
              />
            </div>
          </div>
          <!-- 개인 페이지의 나의 프로필!!! -->
          <div class="row py-7">
            <div class="col-lg-7 col-md-7 z-index-2 position-relative px-md-2 px-sm-5 mx-auto">
              <div class="d-flex justify-content-between align-items-center mb-2">
                <h3 class="mb-0">Woo Migyeong</h3>
                <!-- <div class="d-block">
                  <MaterialButton
                    class="text-nowrap mb-0"
                    variant="outline"
                    color="success"
                    size="sm"
                    >Follow</MaterialButton
                  >
                </div> -->
              </div>
              <!-- <div class="row mb-4">
                <div class="col-auto">
                  <span class="h6 me-1">323</span>
                  <span>Posts</span>
                </div>
                <div class="col-auto">
                  <span class="h6 me-1">3.5k</span>
                  <span>Followers</span>
                </div>
                <div class="col-auto">
                  <span class="h6 me-1">260</span>
                  <span>Following</span>
                </div>
              </div> -->
              <p class="text-lg mb-0">
                저는 부산에 살고 있습니다. 어디가 가장 좋은 여행지였습니다. 어떻게 여행하는 것을
                좋아합니다. 제가 꼭 추천해주고 싶은 여행지는 어디입니다. 여행에 있어서 버킷리스트는
                스카이다이빙입니다.
                <br /><a href="javascript:;" class="text-success icon-move-right"
                  >More about me
                  <i class="fas fa-arrow-right text-sm ms-1"></i>
                </a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
