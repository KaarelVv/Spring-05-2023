
export const validateHttpResponses = (res, target) => {
    if (res.status === 200) {
        return res.json();
    } else if (res.status === 403) {
        return "Sul pole piisavalt õigusi"
    } else if (res.status === 400) {
        return (`${target} ei saa kustutada, see on tellimuses`)
    } else if (res.status === 204) {
        return (`${target} ID on vale`)
    }
}