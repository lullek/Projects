## Pokémon Trivia

This is a web app I made with a group as a project for the course DH2642 at KTH. The different tasks were divided between the group members. I have made the code for the quiz itself (found at https://pokemon-trivia-5c6e2.web.app/#/quiz), the code related to API calls, and promises. The leaderboard was working at the time of the assignment submission, however it seems to not be working as intended now, but this was not a part of what I made for the project anyway.  The website can be found here:

https://pokemon-trivia-5c6e2.web.app/

The git repo for the whole group can be found here:

https://gits-15.sys.kth.se/iprog-students/aldina-kerslow-ohakans-wisj-HT25-Project

In the project, I made:

The side effect-things in mobxReactiveModel.js to check when the Pokémon ID is updated and then update to a new Pokemon (reaction + checkCurrentPokemonIdACB + updateCurrentPokemonIdACB).

Almost everything in PokemonModel.js (generateRandomId, updatePokemonId, getPokeName, getPokeImage, currentIdEffect, parts of addPoint, addCurrentQuestion, resetCurrentPoints, resetCurrentQuestion, resetQuiz, endQuiz and their respective variables and promise holders). It all has to do with the Pokémon quiz itself which I made, to make it exist, work and use the API calls. 

Everything in pokemonSource.js, where the API calls can be found, to fetch the Pokémon names and images (getPokeACB, nameACB, imageACB, getPokemon, getPokemonName and getPokemonImage).

resolvePromise.js (although just copied from the TW).

The whole quizPresenter.jsx, for the logic passed to the quizView, and to conditionally render the quiz, result and suspense views.

Parts of the quizView.jsx to show the quiz.

The suspenseView.jsx (although mostly copied from the code skeleton of an earlier assignment).

Some of the CSS style elements that has to do with the quiz and buttons.

The whole resultView.jsx to show the points and "Play again" button after finished quiz.