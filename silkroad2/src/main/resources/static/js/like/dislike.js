// JavaScript code to handle upvote and downvote
document.addEventListener('DOMContentLoaded', function() {
    const upvoteButtons = document.querySelectorAll('.upvote-button');
    const downvoteButtons = document.querySelectorAll('.downvote-button');

    upvoteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const questionId = this.getAttribute('data-question-id');
            upvoteQuestion(questionId);
        });
    });

    downvoteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const questionId = this.getAttribute('data-question-id');
            downvoteQuestion(questionId);
        });
    });

    function upvoteQuestion(questionId) {
        const upvoteCount = document.querySelector(`.upvote-count[data-question-id="${questionId}"]`);
        const downvoteCount = document.querySelector(`.downvote-count[data-question-id="${questionId}"]`);

        // Remove dislike if previously added
        if (downvoteCount.classList.contains('active')) {
            downvoteCount.classList.remove('active');
            downvoteCount.textContent = parseInt(downvoteCount.textContent) - 1;
        }

        // Add like
        if (!upvoteCount.classList.contains('active')) {
            upvoteCount.classList.add('active');
            upvoteCount.textContent = parseInt(upvoteCount.textContent) + 1;
        }
    }

    function downvoteQuestion(questionId) {
        const upvoteCount = document.querySelector(`.upvote-count[data-question-id="${questionId}"]`);
        const downvoteCount = document.querySelector(`.downvote-count[data-question-id="${questionId}"]`);

        // Remove like if previously added
        if (upvoteCount.classList.contains('active')) {
            upvoteCount.classList.remove('active');
            upvoteCount.textContent = parseInt(upvoteCount.textContent) - 1;
        }

        // Add dislike
        if (!downvoteCount.classList.contains('active')) {
            downvoteCount.classList.add('active');
            downvoteCount.textContent = parseInt(downvoteCount.textContent) + 1;
        }
    }
});
