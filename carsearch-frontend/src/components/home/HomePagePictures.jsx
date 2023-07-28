import React from 'react';
import Carousel from 'react-bootstrap/Carousel';


function HomePagePictures() {
    const imageStyle = {
        width: '500px',
        height: '300px'
    };
    return (
        <div>
            <h5>Some random pictures</h5>
            <Carousel >
                <Carousel.Item>
                    <img
                        style={imageStyle}
                        src="https://images.unsplash.com/photo-1554666869-04dafcdc7a48?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1631&q=80"
                        alt="First slide"
                    />
                </Carousel.Item>
                <Carousel.Item>
                    <img
                        style={imageStyle}
                        src="https://images.unsplash.com/photo-1589240508375-8ad32cfcfcf4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
                        alt="First slide"
                    />
                </Carousel.Item>
                <Carousel.Item>
                    <img
                        style={imageStyle}
                        src="https://images.unsplash.com/photo-1562596133-06ae520e8c7e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1171&q=80"
                        alt="First slide"
                    />
                </Carousel.Item>

            </Carousel>
        </div>
    )
}

export default HomePagePictures