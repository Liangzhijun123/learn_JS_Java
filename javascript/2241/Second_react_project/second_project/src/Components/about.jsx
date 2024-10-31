const About = ({aboutData}) => {
    return ( 
        <>
        <div>
        <h2>{aboutData.title}</h2>
          <h3>{aboutData.description}</h3>
          <div className="quote">{aboutData.quote}</div>
          <h4>--{aboutData.quoteAuthor}</h4>
        </div>
        
        </>
     );
}
 
export default About;