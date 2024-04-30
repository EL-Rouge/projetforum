<template>
  <div>
    <h2>Add a Question</h2>
    <form @submit.prevent="addQuestion" method="POST">
      <label for="titreq">Title</label>
      <input type="text" id="titreq" v-model="question.titreq" required><br>
      <label for="contenuq">Content</label>
      <textarea id="contenuq" v-model="question.contenuq" required></textarea><br>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      question: {
        titreq: '',
        contenuq: ''
      }
    };
  },
  methods: {
    addQuestion() {
      this.question.labels = this.labelInput.split(',').map(label => label.trim());

      // Send a POST request to your backend API to add the question
      fetch('http://localhost:8081/api/add', {
  method: 'POST',   
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(this.question)
})

      .then(response => response.json())
      .then(data => { 
        console.log('Question added:', data);
        // Optionally, you can redirect the user or show a success message
      })
      .catch(error => {
        console.error('Error adding question:', error);
        // Handle error
      });
    }
  }
};
</script>
