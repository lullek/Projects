import { useState } from "react"
import { Routes, Route } from "react-router-dom";
import { Home } from "./presenters/HomePresenter";
import { About } from "./presenters/AboutPresenter";
import { Education } from "./presenters/EducationPresenter";
import { Skills } from "./presenters/SkillsPresenter";
import { Projects } from "./presenters/ProjectsPresenter";
import { Experience } from "./presenters/ExperiencePresenter";
import { Navbar } from "./components/Navbar";
import { Quiz } from "./presenters/QuizPresenter";
import { reactiveModel } from "./mobxReactiveModel";
//import viteLogo from './assets/vite.svg'
import './styles/App.css'

function App() {
  const [language, setLanguage] = useState("swe");

  function toggleLanguage() { setLanguage(language === "swe" ? "eng" : "swe"); };

  return (
  <div>
    <Navbar
      language={language}
      toggleLanguage={toggleLanguage}
    />

    <main>
      <Routes>
        <Route path="/" element={<Home language={language} />} />
        <Route path="/about" element={<About language={language} />} />
        <Route path="/education" element={<Education language={language} />} />
        <Route path="/skills" element={<Skills language={language} />} />
        <Route path="/projects" element={<Projects language={language} />} />
        <Route path="/experience" element={<Experience language={language} />} />
        <Route path="/quiz" element={<Quiz model={reactiveModel} language={language} />} />
      </Routes>
    </main>
  </div>
)
}

export default App;