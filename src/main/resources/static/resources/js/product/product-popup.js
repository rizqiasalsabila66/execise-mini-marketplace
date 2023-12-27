(function(){
        addCloseButtonListener();
        addDetailButtonListener();
    }())
    //function untuk close button formnya
    function addCloseButtonListener(){
        $('.close-button').click(function(event){
            $('.modal-layer').removeClass('modal-layer--opened');
            $('.popup-dialog').removeClass('popup-dialog--opened');
            $('.popup-dialog input').val("");
            $('.popup-dialog textarea').val("");
            $('.popup-dialog .validation-message').text("");

        });
    }

     function addDetailButtonListener(){
            $('.detail-button').click(function(event){
                let id = $(this).attr('data-id');
                $.ajax({
                    url : `/api/product/detail/${id}`,
                    success : function({name,category,description,price,sellerName}){
                        $('.detail-dialog .name').text(name);
                        $('.detail-dialog .category').text(category);
                        $('.detail-dialog .description').text(description);
                        $('.detail-dialog .price').text(price);
                        $('.detail-dialog .sellerName').text(sellerName);
                        $('.modal-layer').addClass('modal-layer--opened');
                        $('.detail-dialog').addClass('popup-dialog--opened');
                    }
                })
            });
        }