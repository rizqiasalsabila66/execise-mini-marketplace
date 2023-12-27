(function(){
        addCloseButtonListener();
        addTopUpButtonListener();
        addSubmitFormListener();
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

    function addTopUpButtonListener(){
        $('.topup-button').click(function(event){
            event.preventDefault();  //mencega pengiriman formulir keserver secaara default
            $('.modal-layer').addClass('modal-layer--opened');
            $('.form-topUp-dialog').addClass('popup-dialog--opened');
        });
    }

    //function untuk save new supplier
    function addSubmitFormListener(){
        $('.form-topUp-dialog button').click(function(event){
            event.preventDefault();
            let dto =
            {
            id:$('.form-topUp-dialog .id').val(),
            balance:$('.form-topUp-dialog .balance').val()
            }
            $.ajax({
                method: "PATCH",
                url : '/api/buyer',
                data : JSON.stringify(dto),
                contentType:'application/json',
                success : function(){
                    location.reload();
                }
            });
        })
    }



