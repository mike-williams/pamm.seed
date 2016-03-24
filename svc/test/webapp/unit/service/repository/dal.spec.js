describe('DAL:', function () {

    beforeEach(angular.mock.module("pamm")); // This loads the module

    beforeEach(angular.mock.inject(function ($injector, _$q_, _dal_, _$rootScope_) {
        $q = _$q_;
        $httpBackend = $injector.get('$httpBackend');
        $httpBackend.when('GET', 'project')
            .respond({test:"test1"});
        $httpBackend.when('POST', 'project')
            .respond({test:"test1"});
        $httpBackend.when('DELETE', 'project/1')
            .respond({test:"test1"});
        dal = _dal_;
        $rootScope = _$rootScope_;
    }));


    it("Ensures when service caller is called with GET relevant mock data is returned", (function () {
        var retrievedData = dal.http.GET("project", null);
        $httpBackend.expectGET('project');
        $rootScope.$apply();
        $httpBackend.flush();
        expect(retrievedData.$$state.value).toEqual({test: "test1"});
    }));

    it("Ensures when service caller GET is called with invalid criteria the promise is rejected", (function () {
        var retrievedData = dal.http.GET("project", {id:1});
        $rootScope.$apply();
        expect(retrievedData.$$state.value).toBeUndefined();
    }));


    it("Ensures when service caller is called with POST relevant mock data is returned", (function () {
        var retrievedData = dal.http.POST("project", {test:"test"});
        $httpBackend.expectPOST('project');
        $rootScope.$apply();
        $httpBackend.flush();
        expect(retrievedData).toBeDefined();
    }));

    it("Ensures when service caller is called with DELETE relevant data is returned", (function () {
        var retrievedData = dal.http.DELETE("project/", {id:"1"});
        $httpBackend.expectDELETE('project/1');
        $rootScope.$apply();
        $httpBackend.flush();
        expect(retrievedData).toBeDefined();
    }));
});
