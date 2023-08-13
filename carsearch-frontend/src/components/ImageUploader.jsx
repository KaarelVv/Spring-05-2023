import React, { useState } from 'react';


const ImageUploader = () => {
    const [selectedFile, setSelectedFile] = useState(null);

    const onFileChange = event => {
        setSelectedFile(event.target.files[0]);
    };

    const onFileUpload = () => {
        const formData = new FormData();

        formData.append(
            "file",
            selectedFile,
            selectedFile.name
        );

        console.log(selectedFile);

        fetch("http://localhost:8080/upload", {
            method: 'POST',
            body: formData
        })
            .then(response => response.text())
            .then(data => {
                console.log(data);
            })
            .catch((error) => {
                console.error(error);
            });
        if (!selectedFile) {
            return <div>No file selected</div>
        }
    };
    return (
        <div>
            <input type="file" onChange={onFileChange} />
            <button onClick={onFileUpload}>Upload!</button>
            {/* <ImageDisplay imageName={selectedFile?.name} /> */}
        </div>
    );
};

export default ImageUploader;
