<script setup lang="ts">
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faHeart as farHeart } from '@fortawesome/free-regular-svg-icons/faHeart';
import { faHeart as fasHeart } from '@fortawesome/free-solid-svg-icons/faHeart';
import { faCircleXmark as farCircleXmark } from '@fortawesome/free-regular-svg-icons/faCircleXmark';
import { faCircleXmark as fasCircleXmark } from '@fortawesome/free-solid-svg-icons/faCircleXmark';
import LikesSidebar from './LikesSidebar.vue';
import ProfileFull from './ProfileFull.vue';
</script>

<template>
  <span @click="showLikes = !showLikes" class="likes-button">
    <FontAwesomeIcon :icon="fasHeart" />
  </span>
  <LikesSidebar
    :likes="likes"
    :showLikes="showLikes"
    :hideLikes="hideLikes"
    :selectProfile="selectProfile"
    :selectedProfile="selectedProfile?.profilePicture"
  />
  <ProfileFull
    v-if="selectedProfile"
    :profilePicture="selectedProfile.profilePicture"
    :profileName="selectedProfile.profileName"
    :profileAge="selectedProfile.profileAge"
    :profileBreed="selectedProfile.profileBreed"
  />
  <div v-else class="card">
    <div class="profile-container">
      <img
        v-if="profilePicture"
        class="profile-picture"
        :src="profilePicture"
      />
      <div v-else class="profile-picture"></div>
      <div class="profile-info">
        <div>{{ profileName }}, {{ profileAge }}</div>
        <div>{{ profileBreed }}</div>
      </div>
    </div>
    <div class="rating-icon-row">
      <FontAwesomeIcon
        @mousedown="crossIconSolid = false"
        @mouseup="crossIconSolid = true"
        @mouseleave="crossIconSolid = true"
        :icon="crossIconSolid ? fasCircleXmark : farCircleXmark"
        @click="getNewProfile"
      />
      <FontAwesomeIcon
        @mousedown="heartIconSolid = false"
        @mouseup="heartIconSolid = true"
        @mouseleave="heartIconSolid = true"
        :icon="heartIconSolid ? fasHeart : farHeart"
        @click="likeProfile"
      />
    </div>
  </div>
</template>

<script lang="ts">
const DOGS_API = 'api/random';
const IMAGES_ROOT = 'https://images.dog.ceo/breeds/'

export default {
  data() {
    return {
      profilePicture: null,
      profileName: '',
      profileAge: null,
      heartIconSolid: true,
      crossIconSolid: true,
      showLikes: false,
      selectedProfile: null,
      likes: [],
    };
  },
  created() {
    this.getNewProfile();
  },
  computed: {
    profileBreed() {
      if (!this.profileName) {
        return '';
      }
      function capitalise(s) {
        return s.charAt(0).toUpperCase() + s.slice(1);
      }
      const breedParts = this.profilePicture.split('\/')[4].split('-');
      if (breedParts.length == 1) {
        return capitalise(breedParts[0]);
      }
      return `${capitalise(breedParts[1])} ${capitalise(breedParts[0])}`;
    },
  },
  methods: {
    async getNewProfile() {
      const response = await fetch(DOGS_API);
      const data = await response.json();
      this.profilePicture = IMAGES_ROOT + data.url;
      this.profileName = data.name;
      this.profileAge = data.age;
    },
    likeProfile() {
      this.likes.unshift({
        profilePicture: this.profilePicture,
        profileName: this.profileName,
        profileAge: this.profileAge,
        profileBreed: this.profileBreed,
      });
      this.getNewProfile();
    },
    hideLikes() {
      this.showLikes = false;
      this.selectedProfile = null;
    },
    selectProfile(profile) {
      this.selectedProfile = profile;
    },
  },
};
</script>

<style scoped>
.card {
  background-color: #495866;
  border-radius: 1rem;
  height: 40rem;
  width: 30rem;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.profile-container {
  position: relative;
  width: 100%;
  height: 80%;
}

.profile-picture {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-info {
  position: absolute;
  bottom: 0;
  left: 0.5rem;
  font-size: 2rem;
  font-weight: bold;
  text-shadow: 1px 1px 1px #000000;
}

.profile-info > div:nth-child(2) {
  font-size: 1.5rem;
}

.rating-icon-row {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  height: 20%;
}

.rating-icon-row > svg {
  font-size: 3rem;
  cursor: pointer;
}

.likes-button {
  position: fixed;
  z-index: 1;
  top: 1rem;
  right: 1rem;
  overflow: hidden;
  background-color: #495866;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-radius: 0.5rem;
  padding: 1rem;
  cursor: pointer;
}

.likes-button > img {
  height: 100%;
}
</style>
