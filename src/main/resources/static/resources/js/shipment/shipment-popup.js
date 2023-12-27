(function(){
        addCloseButtonListener();
        addInsertButtonListener();
        addUpdateButtonListener();
        addSubmitFormListener();
        deleteButtonListener();

    }())

        function addCloseButtonListener(){
            $('.close-button').click(function(event){
                $('.modal-layer').removeClass('modal-layer--opened');
                $('.popup-dialog').removeClass('popup-dialog--opened');
                $('.popup-dialog input').val("");
                $('.popup-dialog textarea').val("");
            });
        }

        function addInsertButtonListener(){
            $('.create-button').click(function(event){
                event.preventDefault();
                $('.modal-layer').addClass('modal-layer--opened');
                $('.form-dialog').addClass('popup-dialog--opened');
            });
        }

        function addUpdateButtonListener(){
            $('.update-button').click(function(event){
                event.preventDefault();
                let id = $(this).attr('data-id');
                $.ajax({
                    url :`/api/shipment/${id}`,
                    success : function(response){
                        populateInputForm(response);
                        $('.modal-layer').addClass('modal-layer--opened');
                        $('.form-dialog').addClass('popup-dialog--opened');

                    }
                })
            });
        }

        function populateInputForm({id,name,price,service}){
             $('.form-dialog .id').val(id);
             $('.form-dialog .name').val(name);
             $('.form-dialog .price').val(price);
             $('.form-dialog .service').val(service);
        }

        function addSubmitFormListener(){
            $('.form-dialog button').click(function(event){
                event.preventDefault();
                let dto = collectInputForm();
                let requestMethod = (dto.id === null) ? 'POST' :'PUT';
                $.ajax({
                    method : requestMethod,
                    url : '/api/shipment',
                    data : JSON.stringify(dto),
                    contentType:'application/json',
                    success : function(response){
                        location.reload();
                    }
                });
            })
        }

        function collectInputForm(){
            let id= $('.form-dialog .id').val()
            let dto = {
                id : (id === "") ? null : id,
                name:$('.form-dialog .name').val(),
                price :$('.form-dialog .price').val(),
                service :$('.form-dialog .service').val()
            }
            return dto;
        }

        function deleteButtonListener(){
                $('.delete-button').click(function(event){
                    event.preventDefault();
                    let id = $(this).attr('data-id');
                    $.ajax({
                        method : 'DELETE',
                        url :`/api/shipment/${id}`,
                        success : function(dependencies){
                        if(dependencies != 0){
                            $('.modal-layer').addClass('modal-layer--opened');
                            $('.form-delete-dialog').addClass('popup-dialog--opened');
                            $('.form-delete-dialog .fail-container .dependencies').text(dependencies);
                        }else{
                        location.reload();
                        }
                    }
                })
            })
            }