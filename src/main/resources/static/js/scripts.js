
/////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////  ADMIN   ////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

//////// SCRIPT ADMIN LIST ADMIN
function fcListCategorieByAdminModal(){

    fcAddCategorieByAdminModal();

    fcEditCategorieByAdminModal();

    fcDeleteCategorieByAdminModal();


    //////// SCRIPT Modal Delete ADMIN
    function fcDeleteCategorieByAdminModal(){
        var deleteAdminBySuperadminModal = document.getElementById('deleteAdminBySuperadminModal')
        var admin = {};

        deleteAdminBySuperadminModal.addEventListener('show.bs.modal', function (event) {
            console.log("deleteAdminBySuperadminModal.addEventListener",event);

            var button = event.relatedTarget
            admin = JSON.parse(button.getAttribute('data-bs-admin'))
            console.log("admin", admin);
            document.getElementById('deleteAdminBySuperadmin-username').innerHTML = admin.firstName +" " + admin.lastName.toUpperCase();
        })

        var deleteAdminBySuperadminForm = document.getElementById('deleteAdminBySuperadmin-form');
        deleteAdminBySuperadminForm.onsubmit = function(event) {

            event.preventDefault();

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('DELETE', deleteAdminBySuperadminForm.getAttribute('action') + "/" + admin.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send();

        }


    }

    //////// SCRIPT Modal Edit ADMIN
    function fcEditCategorieByAdminModal(){
        var editAdminBySuperadminModal = document.getElementById('editAdminBySuperadminModal')
        var admin = {};

        editAdminBySuperadminModal.addEventListener('show.bs.modal', function (event) {
            console.log("editAdminBySuperadminModal.addEventListener",event);

            admin = JSON.parse(event.relatedTarget.getAttribute('data-bs-admin'))
            document.getElementById('editAdminBySuperadmin-firstName').value = admin.firstName;
            document.getElementById('editAdminBySuperadmin-lastName').value = admin.lastName;
            document.getElementById('editAdminBySuperadmin-email').value = admin.username;
            document.getElementById('editAdminBySuperadmin-password').value = admin.password;
            document.getElementById('editAdminBySuperadmin-confirm-password').value = admin.password;

        })

        var editAdminBySuperadminForm = document.getElementById('editAdminBySuperadmin-form');
        editAdminBySuperadminForm.onsubmit = function(event) {

            event.preventDefault();

            var formInputs = editAdminBySuperadminForm.querySelectorAll("input"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }
            console.log("editAdminBySuperadminForm data", data);

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('PUT', editAdminBySuperadminForm.getAttribute('action') + "/" + admin.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }


    }

    //////// SCRIPT Modal Ajout ADMIN
    function fcAddCategorieByAdminModal(){
    console.log("addCategorieByAdminModal");

        var addCategorieByAdminModal = document.getElementById('addCategorieByAdminModal')

        var addCategorieByAdminForm = document.getElementById('addCategorieByAdmin-form');
        addCategorieByAdminForm.onsubmit = function(event) {

            event.preventDefault();

            var formInputs = addCategorieByAdminForm.querySelectorAll("input"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }
                    console.log("data", data);

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log("response", this.response);
            //        window.location.reload();
                }
            }

            httpRequest.open('POST', addCategorieByAdminForm.getAttribute('action'), true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }

    }

}


//////// SCRIPT ADMIN LIST GERANT
function fcListVoitureByAdminModal(){

    fcAddVoitureByAdminModal();

    fcEditVoitureByAdminModal();

    fcDeleteVoitureByAdminModal();


    //////// SCRIPT Modal Delete Voiture
    function fcDeleteVoitureByAdminModal(){
        var deleteVoitureByAdminModal = document.getElementById('deleteVoitureByAdminModal')

        var voiture = {};

        $('#deleteVoitureByAdminModal').on('show.bs.modal', function (event) {
            console.log("deleteVoitureByAdminModal.addEventListener",event);
            voiture = JSON.parse(event.relatedTarget.getAttribute('data-voiture'))

            document.getElementById('deleteVoitureByAdmin-nom').value = voiture.nom;

            console.log("voiture 1", voiture);
        })

        var deleteVoitureByAdminForm = document.getElementById('deleteVoitureByAdmin-form');
        deleteVoitureByAdminForm.onsubmit = function(event) {
            event.preventDefault();

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('DELETE', deleteVoitureByAdminForm.getAttribute('action') + "/" + voiture.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send();

        }


    }

    //////// SCRIPT Modal Edit VOITURE
    function fcEditVoitureByAdminModal(){
        console.log("fcEditVoitureByAdminModal");

        var editVoitureByAdminModal = document.getElementById('editVoitureByAdminModal')

        var voiture = {};

        $('#editVoitureByAdminModal').on('show.bs.modal', function (event) {
            console.log("editVoitureByAdminModal.addEventListener",event);
            voiture = JSON.parse(event.relatedTarget.getAttribute('data-voiture'))

            document.getElementById('editVoitureByAdmin-idCategory').value = voiture.idCategory;

            document.getElementById('editVoitureByAdmin-nom').value = voiture.nom;
            document.getElementById('editVoitureByAdmin-marque').value = voiture.marque;
            document.getElementById('editVoitureByAdmin-modele').value = voiture.modele;

            document.getElementById('editVoitureByAdmin-annee').value = voiture.annee;

            document.getElementById('editVoitureByAdmin-tarif').value = voiture.tarif;
            document.getElementById('editVoitureByAdmin-description').value = voiture.description;

            console.log("voiture 1", voiture);
        })

        var editVoitureByAdminForm = document.getElementById('editVoitureByAdmin-form');
        editVoitureByAdminForm.onsubmit = function(event) {

            event.preventDefault();

            var formInputs = editVoitureByAdminForm.querySelectorAll("input,select,textarea"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            console.log("voiture 2", voiture);

            httpRequest.open('PUT', editVoitureByAdminForm.getAttribute('action') + "/" + voiture.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }

    }

    //////// SCRIPT Modal Ajout Voiture
    function fcAddVoitureByAdminModal(){
        console.log("fcAddVoitureByAdminModal");

        var addVoitureByAdminModal = document.getElementById('addVoitureByAdminModal')

        var addVoitureByAdminForm = document.getElementById('addVoitureByAdmin-form');
        addVoitureByAdminForm.onsubmit = function(event) {

            event.preventDefault();

            var formInputs = addVoitureByAdminForm.querySelectorAll("input,select,textarea"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }

            if( (data.idCategory == "") || (data.idCategory == "0") ) return;
            if( (data.annee == "") || (data.annee == "0") ) return;

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('POST', addVoitureByAdminForm.getAttribute('action'), true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }

    }

}


//////// SCRIPT ADMIN LIST GERANT
function fcListGerantByAdminModal(){

    fcAddGerantByAdminModal();

    fcEditGerantByAdminModal();

    fcDeleteGerantByAdminModal();


    //////// SCRIPT Modal Delete Gerant
    function fcDeleteGerantByAdminModal(){
        var deleteGerantByAdminModal = document.getElementById('deleteGerantByAdminModal')

        var gerant = {};

        $('#deleteGerantByAdminModal').on('show.bs.modal', function (event) {
            console.log("deleteGerantByAdminModal.addEventListener",event);
            gerant = JSON.parse(event.relatedTarget.getAttribute('data-gerant'))

            document.getElementById('deleteGerantByAdmin-username').value = gerant.username;

            console.log("gerant 1", gerant);
        })

        var deleteGerantByAdminForm = document.getElementById('deleteGerantByAdmin-form');
        deleteGerantByAdminForm.onsubmit = function(event) {
            event.preventDefault();

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('DELETE', deleteGerantByAdminForm.getAttribute('action') + "/" + gerant.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send();

        }

    }

    //////// SCRIPT Modal Edit Gerant
    function fcEditGerantByAdminModal(){
        console.log("fcEditGerantByAdminModal");

        var editGerantByAdminModal = document.getElementById('editGerantByAdminModal')

        var gerant = {};

        $('#editGerantByAdminModal').on('show.bs.modal', function (event) {
            console.log("editGerantByAdminModal.addEventListener",event);
            gerant = JSON.parse(event.relatedTarget.getAttribute('data-gerant'))

            document.getElementById('editGerantByAdmin-firstName').value = gerant.firstName;
            document.getElementById('editGerantByAdmin-lastName').value = gerant.lastName;
            document.getElementById('editGerantByAdmin-phoneNumber').value = gerant.phoneNumber;
            document.getElementById('editGerantByAdmin-email').value = gerant.username;
            document.getElementById('editGerantByAdmin-password').value = gerant.password;
            document.getElementById('editGerantByAdmin-confirm-password').value = gerant.password;

            console.log("gerant 1", gerant);
        })

        var editGerantByAdminForm = document.getElementById('editGerantByAdmin-form');
        editGerantByAdminForm.onsubmit = function(event) {

            event.preventDefault();

            var formInputs = editGerantByAdminForm.querySelectorAll("input,select,textarea"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            console.log("gerant 2", gerant);

            httpRequest.open('PUT', editGerantByAdminForm.getAttribute('action') + "/" + gerant.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }

    }

    //////// SCRIPT Modal Ajout Gerant
    function fcAddGerantByAdminModal(){
        console.log("fcAddGerantByAdminModal");

        var addGerantByAdminModal = document.getElementById('addGerantByAdminModal')

        addGerantByAdminModal.addEventListener('show.bs.modal', function (event) {
            console.log("addGerantByAdminModal.addEventListener",event);
        })

        var addGerantByAdminForm = document.getElementById('addGerantByAdmin-form');
        addGerantByAdminForm.onsubmit = function(event) {

            event.preventDefault();

//            var token = addGerantByAdminForm.querySelectorAll("input[name='_csrf']")[0].value;
            var formInputs = addGerantByAdminForm.querySelectorAll("input,select,textarea"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('POST', addGerantByAdminForm.getAttribute('action'), true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
//            httpRequest.setRequestHeader("X-CSRF-TOKEN", token);
            httpRequest.send(JSON.stringify(data));

        }

    }

}



/////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////  GERANT   ////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

function fcListVoitureByGerantModal(){

    fcAddVoitureByGerantModal();

    fcEditVoitureByGerantModal();

    fcDeleteVoitureByGerantModal();


    //////// SCRIPT Modal Delete Voiture
    function fcDeleteVoitureByGerantModal(){
        var deleteVoitureByGerantModal = document.getElementById('deleteVoitureByGerantModal')

        var voiture = {};

        $('#deleteVoitureByGerantModal').on('show.bs.modal', function (event) {
            console.log("deleteVoitureByGerantModal.addEventListener",event);
            voiture = JSON.parse(event.relatedTarget.getAttribute('data-voiture'))

            document.getElementById('deleteVoitureByGerant-nom').value = voiture.nom;

            console.log("voiture 1", voiture);
        })

        var deleteVoitureByGerantForm = document.getElementById('deleteVoitureByGerant-form');
        deleteVoitureByGerantForm.onsubmit = function(event) {
            event.preventDefault();

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('DELETE', deleteVoitureByGerantForm.getAttribute('action') + "/" + voiture.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send();

        }


    }

    //////// SCRIPT Modal Edit VOITURE
    function fcEditVoitureByGerantModal(){
        console.log("fcEditVoitureByGerantModal");

        var editVoitureByGerantModal = document.getElementById('editVoitureByGerantModal')

        var voiture = {};

        $('#editVoitureByGerantModal').on('show.bs.modal', function (event) {
            console.log("editVoitureByGerantModal.addEventListener",event);
            voiture = JSON.parse(event.relatedTarget.getAttribute('data-voiture'))

            document.getElementById('editVoitureByGerant-idCategory').value = voiture.idCategory;

            document.getElementById('editVoitureByGerant-nom').value = voiture.nom;
            document.getElementById('editVoitureByGerant-marque').value = voiture.marque;
            document.getElementById('editVoitureByGerant-modele').value = voiture.modele;

            document.getElementById('editVoitureByGerant-annee').value = voiture.annee;

            document.getElementById('editVoitureByGerant-tarif').value = voiture.tarif;
            document.getElementById('editVoitureByGerant-description').value = voiture.description;

            console.log("voiture 1", voiture);
        })

        var editVoitureByGerantForm = document.getElementById('editVoitureByGerant-form');
        editVoitureByGerantForm.onsubmit = function(event) {

            event.preventDefault();

            var formInputs = editVoitureByGerantForm.querySelectorAll("input,select,textarea"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            console.log("voiture 2", voiture);

            httpRequest.open('PUT', editVoitureByGerantForm.getAttribute('action') + "/" + voiture.id, true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }

    }

    //////// SCRIPT Modal Ajout Voiture
    function fcAddVoitureByGerantModal(){
        console.log("fcAddVoitureByGerantModal");

        var addVoitureByGerantModal = document.getElementById('addVoitureByGerantModal')

        var addVoitureByGerantForm = document.getElementById('addVoitureByGerant-form');
        addVoitureByGerantForm.onsubmit = function(event) {

            event.preventDefault();

            var formInputs = addVoitureByGerantForm.querySelectorAll("input,select,textarea"); // Get the form inputs.

            var data = {};
            for (var i = 0, ii = formInputs.length; i < ii; ++i) {
                data[formInputs[i].name] = formInputs[i].value;
            }

            if( (data.idCategory == "") || (data.idCategory == "0") ) return;
            if( (data.annee == "") || (data.annee == "0") ) return;

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('POST', addVoitureByGerantForm.getAttribute('action'), true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }

    }

}


function fcListReservationByGerantModal(){

    fcValiderReservationByGerantModal();

    fcAnnulerReservationByGerantModal();


    function fcValiderReservationByGerantModal(){
        console.log("fcValiderReservationByGerantModal");

        var validerReservationByGerantModal = document.getElementById('validerReservationByGerantModal')

        var reservation = {};

        $('#validerReservationByGerantModal').on('show.bs.modal', function (event) {
            console.log("validerReservationByGerantModal.addEventListener",event);
            reservation = JSON.parse(event.relatedTarget.getAttribute('data-reservation'))

            console.log("reservation 1", reservation);
        })

        var validerReservationByGerantForm = document.getElementById('validerReservationByGerant-form');
        validerReservationByGerantForm.onsubmit = function(event) {

            event.preventDefault();
            var data = {};

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('PUT', validerReservationByGerantForm.getAttribute('action') + "/" + reservation.id + "/valider", true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));

        }

    }

    function fcAnnulerReservationByGerantModal(){
        console.log("fcAnnulerReservationByGerantModal");

        var annulerReservationByGerantModal = document.getElementById('annulerReservationByGerantModal')

        var reservation = {};

        $('#annulerReservationByGerantModal').on('show.bs.modal', function (event) {
            console.log("annulerReservationByGerantModal.addEventListener",event);
            reservation = JSON.parse(event.relatedTarget.getAttribute('data-reservation'))

            console.log("reservation 1", reservation);
        })

        var annulerReservationByGerantForm = document.getElementById('annulerReservationByGerant-form');
        annulerReservationByGerantForm.onsubmit = function(event) {

            event.preventDefault();

            var data = {};

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('PUT', annulerReservationByGerantForm.getAttribute('action') + "/" + reservation.id + "/annuler", true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));
        }

    }

}



/////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////  UTILISATEUR   /////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

function fcAnnulerReservationByUtilisateurModal(){
        console.log("fcAnnulerReservationByUtilisateurModal");

        var annulerReservationByUtilisateurModal = document.getElementById('annulerReservationByUtilisateurModal')

        var reservation = {};

        $('#annulerReservationByUtilisateurModal').on('show.bs.modal', function (event) {
            console.log("annulerReservationByUtilisateurModal.addEventListener",event);
            reservation = JSON.parse(event.relatedTarget.getAttribute('data-reservation'))

            console.log("reservation 1", reservation);
        })

        var annulerReservationByUtilisateurForm = document.getElementById('annulerReservationByUtilisateur-form');
        annulerReservationByUtilisateurForm.onsubmit = function(event) {

            event.preventDefault();

            var data = {};

            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status != 200) {
                    alert(this.status + ': ' + this.statusText);
                } else {
                    console.log(this.response);
                    window.location.reload();
                }
            }

            httpRequest.open('PUT', annulerReservationByUtilisateurForm.getAttribute('action') + "/" + reservation.id + "/annuler", true);
            httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
            httpRequest.send(JSON.stringify(data));
        }

    }


//////// SCRIPT RESERVER LIST UTILISATEUR
function fcListReserverByUtilisateur(){
     console.log("fcListReserverByUtilisateur");
     $("#addReserverByUtilisateur-form-alert-success").hide();
     $("#addReserverByUtilisateur-form-alert-echec").hide();

     var addReserverByUtilisateurForm = document.getElementById('addReserverByUtilisateur-form');
     addReserverByUtilisateurForm.onsubmit = function(event) {

        event.preventDefault();

        var formInputs = addReserverByUtilisateurForm.querySelectorAll("input,select,textarea"); // Get the form inputs.

        var data = {};
        for (var i = 0, ii = formInputs.length; i < ii; ++i) {
            data[formInputs[i].name] = formInputs[i].value;
        }
        console.log("data", data);

        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function() {
            if (this.readyState != 4) return;
            if (this.status != 200) {
                 $("#addReserverByUtilisateur-form-alert-echec").show();
            } else {
                console.log("response", this.response);
                $("#addReserverByUtilisateur-form-alert-success").show();
                setTimeout(function(){ window.location.reload(); }, 3000);
            }
        }

        httpRequest.open('POST', addReserverByUtilisateurForm.getAttribute('action'), true);
        httpRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        httpRequest.send(JSON.stringify(data));
    }

}


