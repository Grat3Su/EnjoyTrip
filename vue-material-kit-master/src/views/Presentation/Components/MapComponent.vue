<template>
    <section class="Map">
        <div id="map" :options="mapOption"></div>
        <div class="input-group mb-3 mt-3">
            <!-- mb-3 mt-3  추가 -->
            <input
                id="inputSearchWord"
                type="text"
                class="form-control"
                v-model.lazy="mapStore.searchWord"
                placeholder="Search"
                style="background-color: rgb(234, 234, 234)"
                @keyup.enter="searchPlace(mapStore.searchWord)"
            />
            <button
                id="btnSearchWord"
                class="btn btn-success"
                @click="searchPlace(mapStore.searchWord)"
                type="button"
            >
                Search
            </button>
        </div>
        <div class="map-area"></div>
        <br />

        <section class="loc d-flex justify-content-around flex-row align-content-between flex-wrap">
            <div
                class="results row"
                v-for="rs in search.results"
                @click="showPlace(rs)"
                :key="rs.id"
            >
                <div class="card" style="height: 150px; width: 300px; padding: 10px">
                    <div class="card-body">
                        <h5 class="card-title">{{ rs.place_name }}</h5>
                        <p class="card-text">
                            {{ rs.address_name }}
                        </p>
                        <br />
                        <!-- <a href="#" localhref="" class="btn btn-primary">Go somewhere</a> -->
                    </div>
                </div>
            </div>
        </section>
    </section>
    <br />
</template>

<script>
import { ref, watch } from "vue";
import { useMapStore } from "@/stores/mapStore";
// import MarkerHandler from "../views/Presentation/Components/marker-handler";

export default {
    setup() {
        const { mapStore } = useMapStore();
        let mapSearchText = ref(mapStore.searchWord);

        watch(
            () => mapStore.searchWord,
            (cur, prev) => {
                mapSearchText.value = cur;
            }
        );

        return { mapStore, mapSearchText };
    },
    searchText: ref(""),
    name: "Map",
    data() {
        return {
            map: null,
            markers: [],
            mapOption: {
                center: {
                    lat: 35.09362371920619,
                    lng: 128.85603563150357,
                },
                level: 8,
            },
            search: {
                keyword: null,
                pgn: null,
                results: [],
            },
        };
    },
    created() {
        //맵 설정
        // if (window.kakao && window.kakao.maps) {
        //     console.log(1);
        //     this.initMap(); //맵초기화
        // } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
            "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=fd4a1e99d392081cb24edc0c094575aa&libraries=services";
        document.head.appendChild(script);
        //}
    },
    methods: {
        searchPlace(searchText) {
            //장소 찾기
            console.log(searchText);
            const keyword = searchText;
            if (keyword.length === 0) {
                this.search.keyword = null;
                this.search.pgn = null;
                this.search.results = [];
            } else {
                const ps = new kakao.maps.services.Places();
                ps.keywordSearch(keyword, (data, status, pgn) => {
                    this.search.keyword = keyword;
                    this.search.pgn = pgn;
                    this.search.results = data;
                    console.log(pgn);
                });
            }
        },
        initMap() {
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 3,
            };
            this.map = new kakao.maps.Map(container, options);
            this.displayMarker([[this.mapOption.center.lat, this.mapOption.center.lng]]);
        },
        displayMarker(markerPositions) {
            const positions = markerPositions.map((position) => new kakao.maps.LatLng(...position));

            if (positions.length > 0) {
                this.markers = positions.map(
                    (position) =>
                        new kakao.maps.Marker({
                            map: this.map,
                            position,
                        })
                );

                const bounds = positions.reduce(
                    (bounds, latlng) => bounds.extend(latlng),
                    new kakao.maps.LatLngBounds()
                );

                this.map.setBounds(bounds);
            }
        },
        showPlace(place) {
            console.log(place);
            if (this.markers.length > 0) {
                this.markers.forEach((marker) => marker.setMap(null));
            }
            this.mapOption.center = {
                lat: place.y,
                lng: place.x,
            };
            this.markers.push([this.mapOption.center.lat, this.mapOption.center.lng]);
            this.displayMarker([[place.y, place.x]]);
        },
    },
    mounted() {
        // const vueKakaoMap = this.$refs.map;
        // this.markers = new MarkerHandler(vueKakaoMap);
    },
    watch: {
        "options.level"(cur, prev) {
            this.map.setlevel(cur);
        },
        "mapOption.center"(cur, prev) {
            this.map.setCenter(new kakao.maps.LatLng(cur.lat, cur.lng));
        },

        mapSearchText(cur) {
            console.log("test: " + cur);
            this.searchPlace(cur);
        },
    },
};
</script>

<style scoped>
/* .test {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
} */

#map {
    width: 100%;
    height: 400px;
    border: 1px #a8a8a8 solid;
}
/* .map-area {
  display: flex;
} */
</style>
