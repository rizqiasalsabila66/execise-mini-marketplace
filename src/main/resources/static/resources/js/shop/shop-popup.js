(function(){
        addCloseButtonListener();
        addDetailButtonListener();
        addAddButtonListener();
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

     function addAddButtonListener(){
          $('.add-button').click(function(event){
              event.preventDefault();
              let productId = $(this).attr('data-id');
              $('.form-dialog .productId').val(productId);
              $('.modal-layer').addClass('modal-layer--opened');
              $('.form-dialog').addClass('popup-dialog--opened');
              })
      }

      function addSubmitFormListener(){
      $('.form-dialog button').click(function(event){
          event.preventDefault();
          let dto = collectInputForm();
          $.ajax({
              method : 'POST',
              url : '/api/shop',
              data : JSON.stringify(dto),
              contentType:'application/json',
              success : function(response){
                  location.reload();
              }
          });
      })
  }

  function collectInputForm(){
      let dto = {
          quantity:$('.form-dialog .quantity').val(),
          productId :$('.form-dialog .productId').val(),
          shipmentId :$('.form-dialog .shipmentId').val(),
          usernameBuyer :$('.form-dialog .usernameBuyer').val()
      }
      return dto;
  }