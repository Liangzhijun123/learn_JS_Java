// first way
/*
function Weclome(){

}
export default Welcome;
*/

// second way
/*
export default function Welcome (){

}
*/

// third way
// build up our first functional comp
const functionalComp = ({name,job}) => {
    return ( 
        <>
            <h1>
                <h5>{name} does {job}</h5>
            </h1>
        </>
     );
}
 
export default functionalComp;