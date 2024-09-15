<script setup lang="ts">
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faCircleRight } from '@fortawesome/free-regular-svg-icons/faCircleRight';
</script>

<template>
  <div class="likes" :class="{ show: showLikes }">
    <div class="likes-header">
      <span>Likes</span>
      <FontAwesomeIcon @click="hideLikes" :icon="faCircleRight" />
    </div>
    <div
      class="like"
      :class="{ selected: isSelected(profile) }"
      v-for="profile in likes"
      :key="profile.profilePicture"
      @click="selectProfile(profile)"
    >
      {{ profile.profileName }}
    </div>
  </div>
</template>

<script lang="ts">
export default {
  props: [
    'likes',
    'selectedProfile',
    'showLikes',
    'hideLikes',
    'selectProfile',
  ],
  methods: {
    isSelected(profile) {
      return profile.profilePicture === this.selectedProfile;
    },
  },
};
</script>

<style scoped>
.likes {
  position: absolute;
  transform: translateX(100%);
  transition: transform 0.3s;
  top: 0;
  bottom: 0;
  z-index: 1;
  right: 0;
  width: 15rem;
  background-color: #495866;
  overflow-y: auto;
}

.likes.show {
  transform: translateX(0);
}

.likes > .likes-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 0.5rem;
  font-weight: bold;
  background-color: #647a8f;
}

.likes-header > svg {
  cursor: pointer;
}

.likes > .like {
  padding: 0.5rem;
  cursor: pointer;
}

.likes > .like:hover,
.likes > .like.selected {
  background-color: #647a8f;
}
</style>
