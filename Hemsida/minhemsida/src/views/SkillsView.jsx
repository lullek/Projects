export function SkillsView({ language }) {
  return (
    <main>
      <h1>{language === "swe" ? "Kompetenser" : "Skills"}</h1>
      <p>{language === "swe" ? 
      <b>Huvudkompetens:</b>
      : 
      <b>Primary Skills:</b>}</p>
      <p>Java, C#</p>
      <br />

      <p>{language === "swe" ? 
      <b>Webbutveckling:</b>
      : 
      <b>Web Development:</b>}</p>
      <p>JavaScript, React, CSS</p>
      <br />

      <p>{language === "swe" ? 
      <b>Erfarenhet av:</b>
      : 
      <b>Experience with:</b>}</p>
      <p>Python, C, C++, Haskell, Assembly, Prolog, Go</p>
      <br />

      <p>{language === "swe" ? 
      <b>Databaser:</b>
      : 
      <b>Databases:</b>}</p>
      <p>PostgreSQL</p>
      <br />

      <p>{language === "swe" ? 
      <b>Övriga tekniker och verktyg:</b>
      : 
      <b>Other Technologies and Tools:</b>}</p>
      <p>{language === "swe" ? 
      "Git, Unity, MATLAB, machine learning/AI, LaTeX, matematik"
      : 
      "Git, Unity, MATLAB, machine learning/AI, LaTeX, mathematics"}</p>
      <br />

      <p>{language === "swe" ? 
      <b>Språkkunskaper:</b>
      : 
      <b>Languages:</b>}</p>
      <p>{language === "swe" ? 
      "Svenska: Modersmål"
      : 
      "Swedish: Native"}</p>
      <p>{language === "swe" ? 
      "Engelska: Flytande"
      : 
      "English: Fluent"}</p>
    </main>
  );
}