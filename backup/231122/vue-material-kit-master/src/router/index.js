import { createRouter, createWebHistory } from "vue-router";
import PresentationView from "../views/Presentation/PresentationView.vue";
import AboutView from "../views/LandingPages/AboutUs/AboutView.vue";
import InsertBoard from "../views/LandingPages/AboutUs/InsertBoard.vue";
import DetailBoard from "../views/LandingPages/AboutUs/DetailBoard.vue";
import UpdateBoard from "../views/LandingPages/AboutUs/UpdateBoard.vue";
import ContactView from "../views/LandingPages/ContactUs/ContactView.vue";
import AuthorView from "../views/LandingPages/Author/AuthorView.vue";
import AuthorContact from "../views/LandingPages/Author/Sections/AuthorContact.vue";
import SignInBasicView from "../views/LandingPages/SignIn/BasicView.vue";
import PageHeaders from "../layouts/sections/page-sections/page-headers/HeadersView.vue";
import PageFeatures from "../layouts/sections/page-sections/features/FeaturesView.vue";
import NavigationNavbars from "../layouts/sections/navigation/navbars/NavbarsView.vue";
import NavigationNavTabs from "../layouts/sections/navigation/nav-tabs/NavTabsView.vue";
import NavigationPagination from "../layouts/sections/navigation/pagination/PaginationView.vue";
import InputAreasInputs from "../layouts/sections/input-areas/inputs/InputsView.vue";
import InputAreasForms from "../layouts/sections/input-areas/forms/FormsView.vue";
import ACAlerts from "../layouts/sections/attention-catchers/alerts/AlertsView.vue";
import ACModals from "../layouts/sections/attention-catchers/modals/ModalsView.vue";
import ACTooltipsPopovers from "../layouts/sections/attention-catchers/tooltips-popovers/TooltipsPopoversView.vue";
import ElAvatars from "../layouts/sections/elements/avatars/AvatarsView.vue";
import ElBadges from "../layouts/sections/elements/badges/BadgesView.vue";
import ElBreadcrumbs from "../layouts/sections/elements/breadcrumbs/BreadcrumbsView.vue";
import ElButtons from "../layouts/sections/elements/buttons/ButtonsView.vue";
import ElButtonGroups from "../layouts/sections/elements/button-groups/ButtonGroupsView.vue";
import ElDropdowns from "../layouts/sections/elements/dropdowns/DropdownsView.vue";
import ElProgressBars from "../layouts/sections/elements/progress-bars/ProgressBarsView.vue";
import ElToggles from "../layouts/sections/elements/toggles/TogglesView.vue";
import ElTypography from "../layouts/sections/elements/typography/TypographyView.vue";
import MapComponet from "../views/Presentation/Components/MapComponent.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // 지도
      path: "/map",
      name: "map",
      component: MapComponet,
    },
    {
      // 메인 페이지
      path: "/",
      name: "presentation",
      component: PresentationView,
    },
    {
      // 게시판 페이지
      path: "/pages/landing-pages/about-us",
      name: "about",
      component: AboutView,
    },
    {
      // 게시판 페이지 - 글쓰기 페이지
      path: "/pages/landing-pages/about-us/insertBoard",
      name: "insertboard",
      component: InsertBoard,
    },
    {
      // 게시판 페이지 - 상세 페이지
      path: "/pages/landing-pages/about-us/detailBoard",
      name: "detailboard",
      component: DetailBoard,
    },
    {
      // 게시판 페이지 - 수정 페이지
      path: "/pages/landing-pages/about-us/updateBoard",
      name: "updateboard",
      component: UpdateBoard,
    },
    {
      // 회원가입 페이지
      path: "/pages/landing-pages/contact-us",
      name: "contactus",
      component: ContactView,
    },
    {
      // 개인 페이지: 자기 소개 및 좋아요한 여행지 카드형식으로 출력
      path: "/pages/landing-pages/author",
      name: "author",
      component: AuthorView,
    },
    {
      // 개인 페이지: 개인정보 수정
      path: "/pages/landing-pages/author/sections/authorcontact",
      name: "authorcontact",
      component: AuthorContact,
    },
    {
      // 로그인 페이지
      path: "/pages/landing-pages/basic",
      name: "signin-basic",
      component: SignInBasicView,
    },
    {
      // sections 파일 안 값들 다 날리기
      path: "/sections/page-sections/page-headers",
      name: "page-headers",
      component: PageHeaders,
    },
    {
      path: "/sections/page-sections/features",
      name: "page-features",
      component: PageFeatures,
    },
    {
      path: "/sections/navigation/navbars",
      name: "navigation-navbars",
      component: NavigationNavbars,
    },
    {
      path: "/sections/navigation/nav-tabs",
      name: "navigation-navtabs",
      component: NavigationNavTabs,
    },
    {
      path: "/sections/navigation/pagination",
      name: "navigation-pagination",
      component: NavigationPagination,
    },
    {
      path: "/sections/input-areas/inputs",
      name: "inputareas-inputs",
      component: InputAreasInputs,
    },
    {
      path: "/sections/input-areas/forms",
      name: "inputareas-forms",
      component: InputAreasForms,
    },
    {
      path: "/sections/attention-catchers/alerts",
      name: "ac-alerts",
      component: ACAlerts,
    },
    {
      path: "/sections/attention-catchers/modals",
      name: "ac-modals",
      component: ACModals,
    },
    {
      path: "/sections/attention-catchers/tooltips-popovers",
      name: "ac-tooltips-popovers",
      component: ACTooltipsPopovers,
    },
    {
      path: "/sections/elements/avatars",
      name: "el-avatars",
      component: ElAvatars,
    },
    {
      path: "/sections/elements/badges",
      name: "el-badges",
      component: ElBadges,
    },
    {
      path: "/sections/elements/breadcrumbs",
      name: "el-breadcrumbs",
      component: ElBreadcrumbs,
    },
    {
      path: "/sections/elements/buttons",
      name: "el-buttons",
      component: ElButtons,
    },
    {
      path: "/sections/elements/button-groups",
      name: "el-button-groups",
      component: ElButtonGroups,
    },
    {
      path: "/sections/elements/dropdowns",
      name: "el-dropdowns",
      component: ElDropdowns,
    },
    {
      path: "/sections/elements/progress-bars",
      name: "el-progress-bars",
      component: ElProgressBars,
    },
    {
      path: "/sections/elements/toggles",
      name: "el-toggles",
      component: ElToggles,
    },
    {
      path: "/sections/elements/typography",
      name: "el-typography",
      component: ElTypography,
    },
  ],
});
// router.beforeEach((to, from, next) => {
//   let isLogin = false;
//   if (isLogin || to.path == "/" || to.name == "signin-basic") {
//     // 로그인되었거나, 가려고 하는 곳이 메인페이지거나, 로그인페이지이면
//     next();
//   } else {
//     console.log("router-beforeEach - else: 로그인이 안되어서 이동할 수 없다");
//     next("/signin-basic");
//   }
// });
export default router;
