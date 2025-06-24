<%--
  Created by IntelliJ IDEA.
  User: sharo
  Date: 2025-06-23
  Time: 오전 11:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%
    // 파라미터 받기 (상품 상세에서 바로 구매 시)
    String productId = request.getParameter("productId");
    String color = request.getParameter("color");
    String size = request.getParameter("size");
    String quantity = request.getParameter("quantity");

    // 장바구니에서 온 경우와 바로 구매의 경우를 구분
    boolean isDirectPurchase = (productId != null);
%>

<html>
<head>
    <title>Sha_Jang_Tumbler</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-VTmh+5lDQgxBgaA8cD3X2iKQk4YI3sYeEjwA0kaOK1Z3XM3+o2D4w9abEzoS4V6L"
          crossorigin="anonymous"/>

    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"/>
    <link rel="stylesheet" href="/static/css/globals.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/header.jsp" />

<main class="max-w-4xl mx-auto px-4 py-12">
    <h1 class="text-3xl font-light text-gray-900 mb-8">주문/결제</h1>

    <form action="orderProcess" method="post" id="orderForm">
        <div class="grid lg:grid-cols-2 gap-8">
            <!-- 주문 정보 입력 -->
            <div class="space-y-6">
                <!-- 주문자 정보 -->
                <div class="bg-white border border-beige-200 rounded-lg">
                    <div class="p-6 border-b border-beige-100">
                        <h2 class="text-xl font-medium text-gray-900">주문자 정보</h2>
                    </div>
                    <div class="p-6 space-y-4">
                        <div class="grid grid-cols-2 gap-4">
                            <div class="space-y-2">
                                <label for="orderName" class="block text-gray-900 font-medium">이름 *</label>
                                <input type="text" id="orderName" name="orderName" required
                                       class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
                            </div>
                            <div class="space-y-2">
                                <label for="orderPhone" class="block text-gray-900 font-medium">전화번호 *</label>
                                <input type="tel" id="orderPhone" name="orderPhone" placeholder="010-1234-5678" required
                                       class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
                            </div>
                        </div>
                        <div class="space-y-2">
                            <label for="orderEmail" class="block text-gray-900 font-medium">이메일 *</label>
                            <input type="email" id="orderEmail" name="orderEmail" placeholder="your@email.com" required
                                   class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
                        </div>
                    </div>
                </div>

                <!-- 배송 정보 -->
                <div class="bg-white border border-beige-200 rounded-lg">
                    <div class="p-6 border-b border-beige-100">
                        <div class="flex items-center justify-between">
                            <h2 class="text-xl font-medium text-gray-900">배송 정보</h2>
                            <label class="flex items-center gap-2">
                                <input type="checkbox" id="sameAsOrderer" onchange="copyOrdererInfo()">
                                <span class="text-sm text-beige-600">주문자 정보와 동일</span>
                            </label>
                        </div>
                    </div>
                    <div class="p-6 space-y-4">
                        <div class="grid grid-cols-2 gap-4">
                            <div class="space-y-2">
                                <label for="receiverName" class="block text-gray-900 font-medium">받는 분 *</label>
                                <input type="text" id="receiverName" name="receiverName" required
                                       class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
                            </div>
                            <div class="space-y-2">
                                <label for="receiverPhone" class="block text-gray-900 font-medium">전화번호 *</label>
                                <input type="tel" id="receiverPhone" name="receiverPhone" placeholder="010-1234-5678" required
                                       class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
                            </div>
                        </div>

                        <div class="space-y-2">
                            <label for="zipcode" class="block text-gray-900 font-medium">우편번호 *</label>
                            <div class="flex gap-2">
                                <input type="text" id="zipcode" name="zipcode" readonly required
                                       class="flex-1 px-3 py-2 border border-beige-200 rounded-lg bg-beige-50">
                                <button type="button" onclick="searchAddress()"
                                        class="bg-gray-900 hover:bg-beige-800 text-white px-4 py-2 rounded-lg transition-colors">
                                    주소 검색
                                </button>
                            </div>
                        </div>

                        <div class="space-y-2">
                            <label for="address" class="block text-gray-900 font-medium">주소 *</label>
                            <input type="text" id="address" name="address" readonly required
                                   class="w-full px-3 py-2 border border-beige-200 rounded-lg bg-beige-50">
                        </div>

                        <div class="space-y-2">
                            <label for="detailAddress" class="block text-gray-900 font-medium">상세 주소 *</label>
                            <input type="text" id="detailAddress" name="detailAddress" placeholder="아파트, 동/호수" required
                                   class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
                        </div>

                        <div class="space-y-2">
                            <label for="deliveryMemo" class="block text-gray-900 font-medium">배송 메모</label>
                            <select id="deliveryMemo" name="deliveryMemo"
                                    class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none">
                                <option value="">배송 메모를 선택하세요</option>
                                <option value="문 앞에 놓아주세요">문 앞에 놓아주세요</option>
                                <option value="경비실에 맡겨주세요">경비실에 맡겨주세요</option>
                                <option value="택배함에 넣어주세요">택배함에 넣어주세요</option>
                                <option value="부재 시 연락주세요">부재 시 연락주세요</option>
                                <option value="직접입력">직접 입력</option>
                            </select>
                            <textarea id="customMemo" name="customMemo" placeholder="직접 입력하세요" rows="2"
                                      class="w-full px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none hidden"></textarea>
                        </div>
                    </div>
                </div>

                <!-- 결제 방법 -->
                <div class="bg-white border border-beige-200 rounded-lg">
                    <div class="p-6 border-b border-beige-100">
                        <h2 class="text-xl font-medium text-gray-900">결제 방법</h2>
                    </div>
                    <div class="p-6">
                        <div class="space-y-3">
                            <label class="flex items-center space-x-3 p-3 border border-beige-200 rounded-lg hover:bg-beige-50 cursor-pointer">
                                <input type="radio" name="paymentMethod" value="card" class="text-gray-900" required>
                                <div class="flex items-center gap-3">
                                    <i data-lucide="credit-card" class="h-5 w-5 text-gray-900"></i>
                                    <span class="text-gray-900">신용카드</span>
                                </div>
                            </label>
                            <label class="flex items-center space-x-3 p-3 border border-beige-200 rounded-lg hover:bg-beige-50 cursor-pointer">
                                <input type="radio" name="paymentMethod" value="bank" class="text-gray-900">
                                <div class="flex items-center gap-3">
                                    <i data-lucide="building-2" class="h-5 w-5 text-gray-900"></i>
                                    <span class="text-gray-900">무통장입금</span>
                                </div>
                            </label>
                            <label class="flex items-center space-x-3 p-3 border border-beige-200 rounded-lg hover:bg-beige-50 cursor-pointer">
                                <input type="radio" name="paymentMethod" value="kakao" class="text-gray-900">
                                <div class="flex items-center gap-3">
                                    <i data-lucide="smartphone" class="h-5 w-5 text-gray-900"></i>
                                    <span class="text-gray-900">카카오페이</span>
                                </div>
                            </label>
                            <label class="flex items-center space-x-3 p-3 border border-beige-200 rounded-lg hover:bg-beige-50 cursor-pointer">
                                <input type="radio" name="paymentMethod" value="naver" class="text-gray-900">
                                <div class="flex items-center gap-3">
                                    <i data-lucide="smartphone" class="h-5 w-5 text-gray-900"></i>
                                    <span class="text-gray-900">네이버페이</span>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 주문 상품 및 결제 정보 -->
            <div class="space-y-6">
                <!-- 주문 상품 -->
                <div class="bg-white border border-beige-200 rounded-lg sticky top-24">
                    <div class="p-6 border-b border-beige-100">
                        <h2 class="text-xl font-medium text-gray-900">주문 상품</h2>
                    </div>
                    <div class="p-6 space-y-4">
                        <% if(isDirectPurchase) {
                            // 바로 구매의 경우 - 단일 상품
                            String productName = "";
                            String productPrice = "";
                            String productImage = "";

                            switch(productId) {
                                case "1":
                                    productName = "스탠리 아이스플로우 에어로라이트 플립스트로 2.0 텀블러 473ml";
                                    productPrice = "189,000";
                                    productImage = "https://shop-phinf.pstatic.net/20250317_15/1742171755178UDtlA_JPEG/6974354318766909_716864716.jpg?type=m510";
                                    break;
                                case "2":
                                    productName = "스탠리 퀜처 프로투어 플립 스트로 텀블러 887ml, 크림 로즈 골드";
                                    productPrice = "89,000";
                                    productImage = "https://shop-phinf.pstatic.net/20250520_123/1747726280043IgGAP_JPEG/61611558847384767_485628341.jpg?type=m510";
                                    break;
                                case "3":
                                    productName = "스탠리 퀜처 H2.0 플로우스테이트 텀블러 887ml";
                                    productPrice = "245,000";
                                    productImage = "https://shop-phinf.pstatic.net/20250326_81/17429562177615fywk_JPEG/15253103457207392_1327903772.jpg?type=m510";
                                    break;
                                default:
                                    productName = "스탠리 퀜처 H2.0 플로우스테이트 텀블러 591ml";
                                    productPrice = "125,000";
                                    productImage = "https://shop-phinf.pstatic.net/20250228_205/1740706975804d12PN_JPEG/147690168501764_683201606.jpg?type=m510";
                                    break;
                            }

                            int qty = quantity != null ? Integer.parseInt(quantity) : 1;
                            int price = Integer.parseInt(productPrice.replace(",", ""));
                            int totalPrice = price * qty;
                        %>
                        <div class="flex gap-4 p-4 border border-beige-200 rounded-lg">
                            <img src="<%= productImage %>" alt="<%= productName %>"
                                 class="w-16 h-20 object-cover rounded">
                            <div class="flex-1">
                                <h3 class="font-medium text-gray-900 mb-1"><%= productName %></h3>
                                <p class="text-sm text-beige-600 mb-1">색상: <%= color %> / 사이즈: <%= size %></p>
                                <div class="flex justify-between items-center">
                                    <span class="text-sm text-beige-600">수량: <%= qty %>개</span>
                                    <span class="font-medium text-gray-900">₩<%= String.format("%,d", totalPrice) %></span>
                                </div>
                            </div>
                        </div>

                        <!-- Hidden inputs for order processing -->
                        <input type="hidden" name="orderType" value="direct">
                        <input type="hidden" name="productId" value="<%= productId %>">
                        <input type="hidden" name="color" value="<%= color %>">
                        <input type="hidden" name="size" value="<%= size %>">
                        <input type="hidden" name="quantity" value="<%= qty %>">

                        <% } else {
                            // 장바구니에서 온 경우 - 여러 상품 가능
                            int totalPrice = 367000; // 임시 데이터
                        %>
                        <div class="space-y-3">
                            <div class="flex gap-4 p-4 border border-beige-200 rounded-lg">
                                <img src="https://shop-phinf.pstatic.net/20250317_15/1742171755178UDtlA_JPEG/6974354318766909_716864716.jpg?type=m510"
                                     alt="스탠리 아이스플로우 에어로라이트 플립스트로 2.0 텀블러 473ml" class="w-16 h-20 object-cover rounded">
                                <div class="flex-1">
                                    <h3 class="font-medium text-gray-900 mb-1">스탠리 아이스플로우 에어로라이트 플립스트로 2.0 텀블러 473ml</h3>
                                    <p class="text-sm text-beige-600 mb-1">색상: 핑크베이지 / oz: 473ml</p>
                                    <div class="flex justify-between items-center">
                                        <span class="text-sm text-beige-600">수량: 1개</span>
                                        <span class="font-medium text-gray-900">₩189,000</span>
                                    </div>
                                </div>
                            </div>

                            <div class="flex gap-4 p-4 border border-beige-200 rounded-lg">
                                <img src="https://shop-phinf.pstatic.net/20250228_205/1740706975804d12PN_JPEG/147690168501764_683201606.jpg?type=m510"
                                     alt="스탠리 퀜처 H2.0 플로우스테이트 텀블러 591ml" class="w-16 h-20 object-cover rounded">
                                <div class="flex-1">
                                    <h3 class="font-medium text-gray-900 mb-1">스탠리 퀜처 H2.0 플로우스테이트 텀블러 591ml</h3>
                                    <p class="text-sm text-beige-600 mb-1">색상: 하늘 / oz: 591ml</p>
                                    <div class="flex justify-between items-center">
                                        <span class="text-sm text-beige-600">수량: 2개</span>
                                        <span class="font-medium text-gray-900">₩178,000</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="orderType" value="cart">
                        <% } %>

                        <hr class="border-beige-200">

                        <!-- 결제 금액 계산 -->
                        <div class="space-y-2">
                            <div class="flex justify-between text-beige-600">
                                <span>상품 금액</span>
                                <span id="subtotal">₩367000</span>
                            </div>
                            <div class="flex justify-between text-beige-600">
                                <span>배송비</span>
                                <span id="shippingFee">₩3,000</span>
                            </div>
                            <div class="flex justify-between text-green-600">
                                <span>배송비 할인</span>
                                <span id="shippingDiscount">-₩3,000</span>
                            </div>
                            <div class="flex justify-between text-beige-600">
                                <span>할인 금액</span>
                                <span id="discount">-₩0</span>
                            </div>
                            <hr class="border-beige-200">
                            <div class="flex justify-between text-lg font-medium text-gray-900">
                                <span>총 결제 금액</span>
                                <span id="totalAmount">₩367000</span>
                            </div>
                        </div>

                        <!-- 쿠폰 및 적립금 -->
                        <div class="space-y-3 pt-4 border-t border-beige-200">
                            <div>
                                <label class="block text-sm font-medium text-gray-900 mb-2">쿠폰 사용</label>
                                <div class="flex gap-2">
                                    <select class="flex-1 px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none text-sm">
                                        <option value="">사용 가능한 쿠폰이 없습니다</option>
                                    </select>
                                    <button type="button" class="border border-beige-300 text-beige-700 px-3 py-2 rounded-lg text-sm hover:bg-beige-100">
                                        적용
                                    </button>
                                </div>
                            </div>

                            <div>
                                <label class="block text-sm font-medium text-gray-900 mb-2">적립금 사용</label>
                                <div class="flex gap-2">
                                    <input type="number" placeholder="0" min="0" max="5000"
                                           class="flex-1 px-3 py-2 border border-beige-200 rounded-lg focus:border-gray-900 focus:outline-none text-sm">
                                    <button type="button" class="border border-beige-300 text-beige-700 px-3 py-2 rounded-lg text-sm hover:bg-beige-100">
                                        전액사용
                                    </button>
                                </div>
                                <p class="text-xs text-beige-500 mt-1">사용 가능 적립금: 5,000원</p>
                            </div>

                            <!-- 주문 동의 및 결제 -->
                            <div class="bg-white border border-beige-200 rounded-lg">
                                <div class="p-6 space-y-4">
                                    <div class="space-y-3">
                                        <label class="flex items-start gap-3">
                                            <input type="checkbox" required class="mt-1">
                                            <span class="text-sm text-gray-900">
                                                <strong>[필수]</strong> 개인정보 수집·이용 동의
                                                <a href="#" class="text-beige-600 underline ml-1">자세히 보기</a>
                                             </span>
                                        </label>

                                        <label class="flex items-start gap-3">
                                            <input type="checkbox" required class="mt-1">
                                            <span class="text-sm text-gray-900">
                                                <strong>[필수]</strong> 구매조건 확인 및 결제진행 동의
                                                <a href="#" class="text-beige-600 underline ml-1">자세히 보기</a>
                                            </span>
                                        </label>

                                        <label class="flex items-start gap-3">
                                            <input type="checkbox" class="mt-1">
                                            <span class="text-sm text-gray-900">
                                                [선택] 마케팅 정보 수신 동의
                                                <a href="#" class="text-beige-600 underline ml-1">자세히 보기</a>
                                            </span>
                                        </label>
                                    </div>

                                    <button type="submit" class="w-full bg-gray-900 hover:bg-beige-800 text-white py-4 rounded-lg text-lg font-medium transition-colors">
                                        <span id="paymentAmount">₩367000</span> 결제하기
                                    </button>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</main>

<script>
    // 주문자 정보와 배송 정보 동일하게 설정
    function copyOrdererInfo() {
        const checkbox = document.getElementById('sameAsOrderer');
        if (checkbox.checked) {
            document.getElementById('receiverName').value = document.getElementById('orderName').value;
            document.getElementById('receiverPhone').value = document.getElementById('orderPhone').value;
        } else {
            document.getElementById('receiverName').value = '';
            document.getElementById('receiverPhone').value = '';
        }
    }

    // 주소 검색 (실제로는 다음 우편번호 API 등을 사용)
    function searchAddress() {
        // 임시 주소 설정
        document.getElementById('zipcode').value = '06292';
        document.getElementById('address').value = '서울특별시 강남구 테헤란로 123';
        document.getElementById('detailAddress').focus();
    }

    // 배송 메모 직접 입력
    document.getElementById('deliveryMemo').addEventListener('change', function() {
        const customMemo = document.getElementById('customMemo');
        if (this.value === '직접입력') {
            customMemo.classList.remove('hidden');
            customMemo.focus();
        } else {
            customMemo.classList.add('hidden');
        }
    });

    // 폼 제출 처리
    document.getElementById('orderForm').addEventListener('submit', function(e) {
        e.preventDefault();

        // 필수 항목 체크
        const requiredFields = ['orderName', 'orderPhone', 'orderEmail', 'receiverName', 'receiverPhone', 'zipcode', 'address', 'detailAddress'];
        let isValid = true;

        requiredFields.forEach(fieldId => {
            const field = document.getElementById(fieldId);
            if (!field.value.trim()) {
                field.classList.add('border-red-500');
                isValid = false;
            } else {
                field.classList.remove('border-red-500');
            }
        });

        // 결제 방법 선택 체크
        const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked');
        if (!paymentMethod) {
            alert('결제 방법을 선택해주세요.');
            isValid = false;
        }

        if (isValid) {
            // 주문 번호 생성 (임시)
            const orderNumber = 'ORD' + new Date().getTime();

            // 주문 처리 페이지로 이동
            alert('주문이 완료되었습니다.\n주문번호: ' + orderNumber);
            window.location.href = 'order-detail.jsp?orderNumber=' + orderNumber;
        } else {
            alert('필수 항목을 모두 입력해주세요.');
        }

        window.location.href = `/orderProcess`;
    });

    // Initialize Lucide icons
    lucide.createIcons();
</script>
<jsp:include page="../common/footer.jsp" />
</body>
</html>