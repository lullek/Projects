import { getPokemonName , getPokemonImage } from './pokemonSource.js'
import { resolvePromise } from './resolvePromise.js';

export const model={
    numberOfPokemons: 0,
    user: null,
    currentPokemonId: null,
    currentPokemonPromiseState: {},
    currentPokemonName: {},
    currentPokemonImage: {},
    wrongIdOne: null,
    wrongChoiceOne: {},
    wrongIdTwo: null,
    wrongChoiceTwo: {},
    wrongIdThree: null,
    wrongChoiceThree: {},
    choices: [],
    playerCurrentPoints: 0,
    playerHighScore: 0,
    numQuestions: 10,
    currentQuestion: 0,
    quizOngoing: true,
    usersPokemons: [],
    currentLeaderboardPageIndex: 0,

    setNumberOfPokemons(number){
        this.numberOfPokemons = number;
    },

    generateRandomId() {
        return Math.floor(Math.random()*1025) + 1;
    },

    updatePokemonId(id) {
        this.currentPokemonId = id;
    },

    getPokeName(id, promiseState){
        resolvePromise(getPokemonName(id), promiseState);
    },

    getPokeImage(id){
        resolvePromise(getPokemonImage(id), this.currentPokemonImage);
    },

    currentIdEffect(){
        if(!this.currentPokemonId){
            this.currentPokemonName = {};
            this.currentPokemonImage = {};
            return;
        }
        this.getPokeName(this.currentPokemonId, this.currentPokemonName);
        this.getPokeImage(this.currentPokemonId);

        this.wrongIdOne = this.generateRandomId();
        while(this.wrongIdOne == this.currentPokemonId) {
            this.wrongIdOne = this.generateRandomId();
        }

        this.wrongIdTwo = this.generateRandomId();
        while(this.wrongIdTwo == this.currentPokemonId || this.wrongIdTwo == this.wrongIdOne) {
            this.wrongIdTwo = this.generateRandomId();
        }

        this.wrongIdThree = this.generateRandomId();
        while(this.wrongIdThree == this.currentPokemonId || this.wrongIdThree == this.wrongIdOne || this.wrongIdThree == this.wrongIdTwo) {
            this.wrongIdThree = this.generateRandomId();
        }

        this.getPokeName(this.wrongIdOne, this.wrongChoiceOne);
        this.getPokeName(this.wrongIdTwo, this.wrongChoiceTwo);
        this.getPokeName(this.wrongIdThree, this.wrongChoiceThree);
    },

    addPoint(){
        this.playerCurrentPoints = this.playerCurrentPoints + 1;
    },

    addCurrentQuestion(){
        this.currentQuestion = this.currentQuestion + 1;
        if(this.currentQuestion >= this.numQuestions) {
            this.quizOngoing = false;
        }
    },

    resetCurrentPoints(){
        this.playerCurrentPoints = 0;
    },

    resetCurrentQuestion(){
        this.currentQuestion = 0;
    },

    resetQuiz(){
        this.resetCurrentPoints();
        this.resetCurrentQuestion(); //When notifying the user what pokemon can be caught
        this.quizOngoing = true;
        //console.log(this.playerHighScore);
    },

    endQuiz(){
        if(this.playerCurrentPoints > this.playerHighScore) {
            this.playerHighScore = this.playerCurrentPoints;
        }
        this.quizOngoing = false;
    },
    
    setCurrentLeaderboardPageIndex(index){
        this.currentLeaderboardPageIndex = this.currentLeaderboardPageIndex + index;
    }
};

