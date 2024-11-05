import PeopleModal from "./peopleModal";

const PeopleGroups = ({ title, obj, group }) => {
    const peopleList = obj && obj[group]; 

    return ( 
        <>
            <h3>{title}</h3>
            <div className="peopleList">
                {peopleList ? (
                    peopleList.map((p) => 
                        <div key={p.username} className="peopleListItem">
                            <h3>{p.name}</h3>
                            <img src={p.imagePath} alt={`${p.name}'s profile`} />
                            <PeopleModal {...p} />
                        </div>
                    )
                ) : (
                    <p>No data available.</p>
                )}
            </div>
        </>
    );
}

export default PeopleGroups;
