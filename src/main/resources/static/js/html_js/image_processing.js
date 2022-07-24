window.onload = function () {//算法参考https://www.jb51.net/article/123995.htm

    var canvas = document.getElementById("canvas");//获取canvas画布对象
    context = canvas.getContext('2d');//获取2D上下文对象，大多数Canvas API均为此对象方法
    var image = new Image();//定义一个图片对象
    image.src = "convertImages/WCDV201903071209277SVM.jpg";
    image.width= 1400;
    image.height=400;


    image.onload = function () { //此处必须注意！后面所有操作均需在图片加载成功后执行，否则图片将处理无效
        context.drawImage(image,0,0,image.width,image.height);//将图片从Canvas画布的左上角(0,0)位置开始绘制，大小默认为图片实际大小\

        var imageData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象
        var ImageObject = new Array();
        var ForwardImageObject = new Array();
        ImageObject.push(imageData);


        var fanse = document.getElementById("fanse");
        fanse.onclick = function(){ // 单击“处理图片”按钮，处理图片

            var imageData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象
            var imageData_length = imageData.data.length / 4;
            // 解析之后进行算法运算
            for (var i = 0; i < imageData_length; i++) {
                imageData.data[i * 4] = 255 - imageData.data[i * 4];
                imageData.data[i * 4 + 1] = 255 - imageData.data[i * 4 + 1];
                imageData.data[i * 4 + 2] = 255 - imageData.data[i * 4 + 2];
            }
            context.putImageData(imageData, 0, 0);
            ImageObject.push(imageData);


        };

        var brightness = document.getElementById("brightness");
        brightness.onclick = function () {
            var adjust = brightness.value;
            var oDiv = document.getElementById("div_brightness");
            oDiv.innerHTML = adjust;
            //var adjust = 10;
            adjust = Math.floor(255 * (adjust / 100));
            var imageData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象
            var imageData_length = imageData.data.length / 4;
            // 解析之后进行算法运算
            for (var i = 0; i < imageData_length; i++) {
                imageData.data[i * 4] += adjust;
                imageData.data[i * 4 + 1] += adjust;
                imageData.data[i * 4 + 2] += adjust;
            }
            context.putImageData(imageData, 0, 0);
            ImageObject.push(imageData);


        };



        // var ForwardStep = document.getElementById("ForwardStep");
        // ForwardStep.onclick = function(){ // 单击“处理图片”按钮，处理图片
        //
        //     var DelImageData = ImageObject.pop();
        //     var ImageObjectLength = ImageObject.length;
        //     if(ImageObjectLength == 0){
        //
        //         ImageObject.push(DelImageData);
        //
        //     }else{
        //
        //         var TempImageDate = ImageObject.pop();
        //         context.putImageData(TempImageDate, 0, 0);
        //         ImageObject.push(TempImageDate);
        //
        //     }
        //
        // };



        var returnStep = document.getElementById("returnStep");
        returnStep.onclick = function(){ // 单击“处理图片”按钮，处理图片

            var DelImageData = ImageObject.pop();
            var ImageObjectLength = ImageObject.length;
            if(ImageObjectLength == 0){

                ImageObject.push(DelImageData);

            }else{

                var TempImageDate = ImageObject.pop();
                context.putImageData(TempImageDate, 0, 0);
                ImageObject.push(TempImageDate);

            }

        };





        var imageHRevert = document.getElementById("imageHRevert");
        imageHRevert.onclick = function () {


            var imageData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象
            // 解析之后进行算法运算
            var newData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象;

            for(var i=0,h=imageData.height;i<h;i++){
                for(j=0,w=imageData.width;j<w;j++){

                    newData.data[i*w*4+j*4+0] = imageData.data[i*w*4+(w-j)*4+0];
                    newData.data[i*w*4+j*4+1] = imageData.data[i*w*4+(w-j)*4+1];
                    newData.data[i*w*4+j*4+2] = imageData.data[i*w*4+(w-j)*4+2];
                    newData.data[i*w*4+j*4+3] = imageData.data[i*w*4+(w-j)*4+3];
                }}
            context.putImageData(newData, 0, 0);
            ImageObject.push(imageData);


        };

        var imageVRevert = document.getElementById("imageVRevert");
        imageVRevert.onclick = function () {


            var imageData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象
            // 解析之后进行算法运算
            var newData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象;

            for(var i=0,h=imageData.height;i<h;i++){
                for(j=0,w=imageData.width;j<w;j++){

                    newData.data[i*w*4+j*4+0] = imageData.data[(h-i)*w*4+j*4+0];
                    newData.data[i*w*4+j*4+1] = imageData.data[(h-i)*w*4+j*4+1];
                    newData.data[i*w*4+j*4+2] = imageData.data[(h-i)*w*4+j*4+2];
                    newData.data[i*w*4+j*4+3] = imageData.data[(h-i)*w*4+j*4+3];

                }}
            context.putImageData(newData, 0, 0);
            ImageObject.push(imageData);


        };





        var contrast = document.getElementById("contrast");
        contrast.onclick = function () {
            //var adjust = 10;
            var adjust = contrast.value;
            var oDiv = document.getElementById("div_contrast");
            oDiv.innerHTML = adjust;
            adjust = (adjust/100) + 1;
            var imageData = context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象 https://stackoverflow.com/questions/10521978/html5-canvas-image-contrast
            var newData =  context.getImageData(0, 0, canvas.width, canvas.height); //获取图片数据对象
            var imageData_length = imageData.data.length /4;
            // 解析之后进行算法运算
            var intercept = 128 * (1 - adjust);
            for (var i = 0; i < imageData_length; i++) {

                imageData.data[i * 4] = (imageData.data[i * 4])*adjust + intercept;
                imageData.data[i * 4 + 1] = (imageData.data[i * 4 + 1])*adjust + intercept;
                imageData.data[i * 4 + 2] = (imageData.data[i * 4 + 2])*adjust + intercept;

            }
            context.putImageData(imageData, 0, 0);
            ImageObject.push(imageData);


        };





    }







}



