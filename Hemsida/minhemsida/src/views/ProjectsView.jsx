import {Carousel} from "../components/Carousel.jsx";

export function ProjectsView({ language }) {
  return (
    <main>
      <h1>{language === "swe" ? "Projekt" : "Projects"}</h1>
      <Carousel language = {language}/>
    </main>
  );
}