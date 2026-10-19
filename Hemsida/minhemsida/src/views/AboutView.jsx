export function AboutView({ language }) {
  return (
    
    <main className="text">
      <h1>{language === "swe" ? "Om mig" : "About me"}</h1>
      <p>{language === "swe" ? 
      "Civilingenjör i datateknik med master i datalogi från KTH. Jag har en stark grund inom programmering, matematik och datalogi samt praktisk erfarenhet av teknisk problemlösning och service genom arbete på KTH:s IT-avdelning. Jag har tidigare arbetat inom service, vård och skola." 
      : 
      "Master of Science in Engineering (Computer Science) from KTH. I have a strong foundation in programming, mathematics, and computer science, as well as practical experience in technical problem-solving through my work at KTH's IT department. I have previously worked with service, healthcare, and education."}</p>
      <br />
      <p>{language === "swe" ? 
      "Jag är analytisk, noggrann och trivs med att förstå system på djupet. Både hur de är uppbyggda och hur de upplevs av människor. Jag kan bidra med teknisk kompetens, nyfikenhet och en vilja att ständigt utvecklas."
      : 
      "I am analytical, detail-oriented, and enjoy developing a deep understanding of systems, both how they are built and how they are experienced by users. I can contribute with technical expertise, curiosity, and a strong desire to learn and improve."}</p>
    </main>
  );
}