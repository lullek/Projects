export function EducationView({ language }) {
  return (
    <main>
      <h1>{language === "swe" ? "Utbildning" : "Education"}</h1>
      <p>{language === "swe" ? 
      <b>Kungliga Tekniska Högskolan</b>
      : 
      <b>Royal Institute of Technology</b>}</p>

      <p>{language === "swe" ? 
      "Civilingenjörsprogrammet i datateknik"
      : 
      "Master of Science in Engineering, Computer Science"}</p>

      <p>{language === "swe" ? 
      "Master i datalogi"
      : 
      "Master's in Computer Science"}</p>

      <p>2020 - 2026</p>

      <br />
      <p>{language === "swe" ? 
      "Examensarbete:"
      : 
      "Master's Thesis:"}</p>
      <a href="https://kth.diva-portal.org/smash/record.jsf?pid=diva2:2005886" target="_blank" rel="noopener noreferrer">Extending Procedural Generation Methods That Use Cellular Automata to Generate Urban Road Patterns</a>
      
      <br />
      <br />

      <p>{language === "swe" ? 
      <b>Rytmus musikergymnasiet</b>
      : 
      <b>Rytmus Music High School</b>}</p>
      <p>{language === "swe" ? 
      "Estetiska linjen - elgitarr"
      : 
      "Arts Program - Electric Guitar"}</p>

      <p>2005 - 2008</p>
    </main>
  );
}