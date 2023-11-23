<script setup>
import { onMounted, onUnmounted } from "vue";

//example components
import DefaultFooter from "../../examples/footers/FooterDefault.vue";
import Header from "../../examples/Header.vue";

//images
import vueMkHeader from "@/assets/img/vue-mk-header.jpg";

//map
import mapComponent from "./Components/MapComponent.vue";
import attrComponent from "./Components/AttrComponent.vue";

//hooks
const body = document.getElementsByTagName("body")[0];
onMounted(() => {
    body.classList.add("presentation-page");
    body.classList.add("bg-gray-200");
});
onUnmounted(() => {
    body.classList.remove("presentation-page");
    body.classList.remove("bg-gray-200");
});
</script>

<template>
    <Header>
        <div
            class="page-header min-vh-75"
            :style="`background-image: url(${vueMkHeader})`"
            loading="lazy"
        >
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 text-center mx-auto position-relative">
                        <h1
                            class="text-white pt-3 mt-n5 me-2"
                            :style="{ display: 'inline-block ' }"
                        >
                            Voyage Hub!
                        </h1>
                        <p class="lead text-white px-5 mt-3" :style="{ fontWeight: '500' }">
                            당신이 찾는 모든 여행지 정보를 Voyage Hub에서 한 눈에 확인할 수
                            있습니다!
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </Header>

    <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6">
        <map-component></map-component>
        <br />
        <h1>여행지 찾기</h1>
        <br />
        <attr-component></attr-component>
    </div>
    <DefaultFooter />
</template>
