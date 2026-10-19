import { shufflePokemons } from '/src/utilities';
import '/src/styles/Quizpage.css';

export function QuizView(props) {
    
    function chooseAnswerACB(choice) {
        console.log(choice)
        const correct = choice === props.currentPokemonName;
        props.onChooseAnswer(correct)
    }

    function pokemonQuizAlternativesCB(array){
        return(
        <div>
        <div> <button className="answerButton" onClick={() => chooseAnswerACB(array[0])}>{array[0]}</button> <button className="answerButton" onClick={() => chooseAnswerACB(array[1])}>{array[1]}</button> </div>
        <div> <button className="answerButton" onClick={() => chooseAnswerACB(array[2])}>{array[2]}</button> <button  className="answerButton" onClick={() => chooseAnswerACB(array[3])}>{array[3]}</button> </div> 
        </div>
        );
    }
    
    return (
        
        <div className="quizCSS"> 
        
        <div>{props.language === "swe" ? 
              "Vad heter denna Pokémon?" 
              : 
              "What is the name of this Pokémon?"} </div> 
        <br />
        <div>
            <span>{props.language === "swe" ? 
                  "Poäng: " 
                  : 
                  "Score: "}{props.playerCurrentPoints}</span>
        </div>      


        <div> <img className="quizPokemonImage" src={props.currentPokemonImage} alt="pokemon"/> </div> 
        <div className="buttonContainer">
            {pokemonQuizAlternativesCB(shufflePokemons([props.currentPokemonName, props.wrongOne, props.wrongTwo, props.wrongThree]))}
        </div>
        </div> 
    );
}