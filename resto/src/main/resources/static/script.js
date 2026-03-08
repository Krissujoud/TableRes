async function search() {

    let people = document.getElementById("people").value
    let zone = document.getElementById("zone").value

    let res = await fetch("/api/search", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({
            peopleCount: people,
            zone: zone
        })
    })

    let tables = await res.json()

    let floor = document.getElementById("floor")
    floor.innerHTML=""

    tables.forEach(t => {

        let div = document.createElement("div")

        if(t.recommended)
            div.className="table recommended"
        else if(t.reserved)
            div.className="table reserved"
        else
            div.className="table free"

        div.style.left = t.x + "px"
        div.style.top = t.y + "px"

        floor.appendChild(div)
    })
}