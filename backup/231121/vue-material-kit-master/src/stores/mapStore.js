import { ref, reactive } from "vue";
import { defineStore } from "pinia";

export const useMapStore = defineStore("mapStore", () => {
    const mapStore = reactive({
        searchWord: "",
        attrList: [],
    });

    return { mapStore };
});
