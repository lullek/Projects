import {observer} from "mobx-react-lite";
import {QuizView} from "../views/QuizView";
import { SuspenseView } from "/src/views/SuspenseView.jsx";
import { ResultView } from "/src/views/ResultView.jsx";

const Quiz = observer(function QuizRender(props){

        function chooseACB(choice) {
            if(choice) {
                props.model.addPoint();
            }

            props.model.addCurrentQuestion();
            props.model.updatePokemonId(props.model.generateRandomId());
        }

        function playAgainACB() {
            props.model.resetQuiz();
        }

        function decideWhatToRender() {
            if(props.model.quizOngoing) {
                if(!props.model.currentPokemonName.data || !props.model.currentPokemonImage.data || !props.model.wrongChoiceOne.data || !props.model.wrongChoiceTwo.data || !props.model.wrongChoiceThree.data) {
                    return <SuspenseView promiseOne={props.model.currentPokemonName.promise} errorOne={props.model.currentPokemonName.error} promiseTwo={props.model.currentPokemonImage.promise} errorTwo={props.model.currentPokemonImage.error} promiseThree={props.model.wrongChoiceOne.promise} errorThree={props.model.wrongChoiceOne.error} promiseFour={props.model.wrongChoiceTwo.promise} errorFour={props.model.wrongChoiceTwo.error} promiseFive={props.model.wrongChoiceThree.promise} errorFive={props.model.wrongChoiceThree.error}/>;
                } else {
                    return <QuizView currentPokemonName={props.model.currentPokemonName.data} 
                    currentPokemonImage={props.model.currentPokemonImage.data} 
                    wrongOne={props.model.wrongChoiceOne.data} 
                    wrongTwo={props.model.wrongChoiceTwo.data} 
                    wrongThree={props.model.wrongChoiceThree.data} 
                    playerCurrentPoints={props.model.playerCurrentPoints}
                    onChooseAnswer={chooseACB}
                    currentQuestion={props.model.currentQuestion}
                    language={props.language}/>; 
                }
            } else {
                return <ResultView points={props.model.playerCurrentPoints} onPlayAgain={playAgainACB} language={props.language}/>;
            }
        }
        
        return <div>{decideWhatToRender()}</div>
    }

);

export {Quiz};