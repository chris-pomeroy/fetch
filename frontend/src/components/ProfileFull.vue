<template>
  <div class="profile">
    <img
      v-show="profilePicture"
      class="profile-picture"
      :src="profilePicture"
    />
    <div class="profile-info">
      <div>{{ profileName }}, {{ profileAge }}</div>
      <div>{{ profileBreed }}</div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  props: ['profilePicture', 'profileName', 'profileAge', 'profileBreed'],
};
</script>

<style scoped>
.profile {
  position: relative;
  background-color: #495866;
  border-radius: 1rem;
  height: 40rem;
  width: 40rem;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.profile-picture {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-info {
  position: absolute;
  bottom: 0.5rem;
  left: 0.5rem;
  font-size: 2rem;
  font-weight: bold;
  text-shadow: 1px 1px 1px #000000;
}

.profile-info > div:nth-child(2) {
  font-size: 1.5rem;
}
</style>
