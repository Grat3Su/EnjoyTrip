<script setup>
// example components
import TransparentBlogCard from "../../../../examples/cards/blogCards/TransparentBlogCard.vue";

//Vue Material Kit 2 components
import post1 from "@/assets/img/examples/testimonial-6-2.jpg";
import post2 from "@/assets/img/examples/testimonial-6-3.jpg";
import post3 from "@/assets/img/examples/blog-9-4.jpg";
</script>
<template>
  <section class="py-3">
    <div class="container">
      <div class="row">
        <div class="col-lg-6">
          <h3 class="mb-5">Check My Favorite Attractions!</h3>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post1"
            title="인천 소야도"
            description="1박 2일 여행지, 인천 섬 여행지로 유명한 덕적도와 가까운 작은 섬으로 두 섬 사이에 다리가 개통되어 여행객이 늘어나고 있지만, 아직 많은 사람이 모이지 않아 조용한 섬 여행을 누리기에 안성맞춤이다. 낚시나 해루질 갯벌체험 등이 가능해 서해 가족 여행지로 추천하는 곳."
          />
        </div>
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post2"
            title="한산도 통영"
            description="역사책에서 지명은 한번 들어본 곳이지만 제대로 여행하는 경우는 많지 않다. 한산도 안에는 통제영 오토캠핑장이 있어 최근 유행하는 국내 차박여행이나 카크닉을 즐기기에 딱인 곳. 국내 언택트 여행지로도 추천한다. 물론 풍경 역시 빼어나 남해의 깊고 푸른 바다는 선물처럼 눈앞에 펼쳐져 있을 것이다. 이순신 장군을 기리는 제승당은 산책하기에 좋아 빼놓을 수 없는 한산도 여행 코스이다. 다리로 연결된 추봉도에서 해수욕을 즐기는 것도 추천한다."
          />
        </div>
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post3"
            title="제주도 비양도"
            description="협재해변 등지에서 볼 수 있는 한림읍의 비양도와 우도에서 다시 버스와 도보로 이동하는 우도면의 비양도가 그 두 곳. 쉽게 지나칠 수 있지만, 아는 사람들은 절대 허투루 생각하지 않는 섬 속의 섬이다. 우선 서부 한림읍의 비양도는 물감을 푼 듯 투명한 해변과 아기자기한 동산을 산책하기에 딱인 곳. 우도면의 비양도는 사실 국내 백팩킹의 성지로 불리는 곳으로, 말도 안 되는 노을 풍경부터 밤하늘의 쏟아질 듯한 별, 부지런한 자만 누릴 수 있는 일출까지 제주의 풍경을 300% 즐길 수 있는 곳이다. 서로 다른 이 두 곳을 모두 즐겨보자."
          />
        </div>
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post3"
            title="고창 전라북도"
            description="전주, 군산, 담양 등 전라도 여행지에 가려져 있지만 고창 자체로도 국내 1박 2일 여행을 알차게 채울 수 있다. 4~5월에는 푸른 고창 청보리밭이 매력적인 곳. 또한, 고인돌 유적도 구경할 수 있다. 이곳의 고인돌 유적은 규모가 크고 다양하게 모여있어 어린아이들도, 어른들도 아주 흥미롭게 구경할 수 있는 것이 장점. 고즈넉한 선운사도 가벼운 산책을 즐길 수 있는 곳이니 빼놓지 말자. 또한, 고창에서는 통통하고 고소한 장어와 이곳에서만 맛볼 수 있는 짜장과 국물 없는 짬뽕을 섞어 먹는 짬짜면을 추천한다."
          />
        </div>
      </div>
      <div class="row">
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post1"
            title="인천 소야도"
            description="1박 2일 여행지, 인천 섬 여행지로 유명한 덕적도와 가까운 작은 섬으로 두 섬 사이에 다리가 개통되어 여행객이 늘어나고 있지만, 아직 많은 사람이 모이지 않아 조용한 섬 여행을 누리기에 안성맞춤이다. 낚시나 해루질 갯벌체험 등이 가능해 서해 가족 여행지로 추천하는 곳."
          />
        </div>
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post2"
            title="한산도 통영"
            description="역사책에서 지명은 한번 들어본 곳이지만 제대로 여행하는 경우는 많지 않다. 한산도 안에는 통제영 오토캠핑장이 있어 최근 유행하는 국내 차박여행이나 카크닉을 즐기기에 딱인 곳. 국내 언택트 여행지로도 추천한다. 물론 풍경 역시 빼어나 남해의 깊고 푸른 바다는 선물처럼 눈앞에 펼쳐져 있을 것이다. 이순신 장군을 기리는 제승당은 산책하기에 좋아 빼놓을 수 없는 한산도 여행 코스이다. 다리로 연결된 추봉도에서 해수욕을 즐기는 것도 추천한다."
          />
        </div>
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post3"
            title="제주도 비양도"
            description="협재해변 등지에서 볼 수 있는 한림읍의 비양도와 우도에서 다시 버스와 도보로 이동하는 우도면의 비양도가 그 두 곳. 쉽게 지나칠 수 있지만, 아는 사람들은 절대 허투루 생각하지 않는 섬 속의 섬이다. 우선 서부 한림읍의 비양도는 물감을 푼 듯 투명한 해변과 아기자기한 동산을 산책하기에 딱인 곳. 우도면의 비양도는 사실 국내 백팩킹의 성지로 불리는 곳으로, 말도 안 되는 노을 풍경부터 밤하늘의 쏟아질 듯한 별, 부지런한 자만 누릴 수 있는 일출까지 제주의 풍경을 300% 즐길 수 있는 곳이다. 서로 다른 이 두 곳을 모두 즐겨보자."
          />
        </div>
        <div class="col-lg-3 col-sm-6">
          <TransparentBlogCard
            :image="post3"
            title="고창 전라북도"
            description="전주, 군산, 담양 등 전라도 여행지에 가려져 있지만 고창 자체로도 국내 1박 2일 여행을 알차게 채울 수 있다. 4~5월에는 푸른 고창 청보리밭이 매력적인 곳. 또한, 고인돌 유적도 구경할 수 있다. 이곳의 고인돌 유적은 규모가 크고 다양하게 모여있어 어린아이들도, 어른들도 아주 흥미롭게 구경할 수 있는 것이 장점. 고즈넉한 선운사도 가벼운 산책을 즐길 수 있는 곳이니 빼놓지 말자. 또한, 고창에서는 통통하고 고소한 장어와 이곳에서만 맛볼 수 있는 짜장과 국물 없는 짬뽕을 섞어 먹는 짬짜면을 추천한다."
          />
        </div>
      </div>
    </div>
  </section>
</template>
