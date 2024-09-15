<script setup lang="ts">
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

const props = defineProps(['icon', 'onClickIcon']);
</script>

<template>
  <FontAwesomeIcon
    @mousedown="setClicked(true)"
    @mouseup="setClicked(false)"
    @mouseleave="setClicked(false)"
    :icon="icon"
  />
</template>

<script lang="ts">
export default {
  data() {
    return {
      clicked: false,
    };
  },
  computed: {
    icon() {
      return this.clicked ? this.props.onClickIcon : this.props.icon;
    },
  },
  methods: {
    setClicked(value) {
      this.clicked = value;
    },
  },
};
</script>
