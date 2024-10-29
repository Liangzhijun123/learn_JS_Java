// https://solace.ist.rit.edu/~dsbics/proxy/https://ischool.gccis.rit.edu/api/
// https://people.rit.edu/~dsbics/proxy/https://ischool.gccis.rit.edu/api/

const proxyServer = 'https://solace.ist.rit.edu/~dsbics/proxy/https://ischool.gccis.rit.edu/api/';

// endpoint should be something like 'about/' or 'degrees/'
async function getData(endpoint){
    const result = await fetch(`${proxyServer}${endpoint}`);
    return await result.json();
}

export default getData;