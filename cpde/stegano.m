img1 = imread('bact.jpg');
img1 = rgb2gray(img1);
img1 = imresize(img1,[225 225]);
subplot(3,2,1);
imshow(img1);
title('Image to be encoded');

img2 = imread('per.jpeg');
img2 = rgb2gray(img2);
subplot(3,2,2);
imshow(img2);
title('Original Image');

a1=bitand(img1,128);
a1=a1/128;
subplot(3,2,3);
imshow(logical(a1));
title('MSB of the image');

a2 = bitand(img2,254) + a1;
subplot(3,2,4);
imshow(a2);
title('Encoded image');

b1 = bitand(a2,1);
subplot(3,2,5);
imshow(logical(b1));
title('Reconstructed image');

b2 = a2 - b1;
subplot(3,2,6);
imshow(b2);
title('Decode image');