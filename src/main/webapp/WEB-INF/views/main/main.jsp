<%--
  Created by IntelliJ IDEA.
  User: sharo
  Date: 2025-06-22
  Time: 오후 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<main>
    <!-- Hero Section -->
    <section class="relative h-screen flex items-center justify-center bg-gradient-to-r from-beige-100 to-beige-200">
        <div class="text-center max-w-4xl mx-auto px-4">
            <h1 class="text-5xl md:text-7xl font-light text-gray-900 mb-6">
                Quencher Tumbler<br>
                <span class="text-beige-600">Large </span>
            </h1>
            <p class="text-xl text-beige-700 mb-8 max-w-2xl mx-auto">
                아침의 따뜻한 커피부터 갈증을 해소하는 시원한 물 한 잔까지 당신의 모든 순간을 함께
            </p>
            <div class="flex gap-4 justify-center">
                <button class="bg-gray-900 hover:bg-beige-800 text-white px-8 py-3 rounded-lg flex items-center gap-2">
                    컬렉션 보기
                    <i data-lucide="arrow-right" class="h-5 w-5"></i>
                </button>
                <button class="border border-gray-900 text-gray-900 hover:bg-gray-900 hover:text-white px-8 py-3 rounded-lg">
                    브랜드 스토리
                </button>
            </div>
        </div>
    </section>

    <!-- Featured Products -->
    <section class="py-16 px-4">
        <div class="max-w-7xl mx-auto">
            <div class="text-center mb-12">
                <h2 class="text-3xl font-light text-gray-900 mb-4">STANLEY COLLECTION</h2>
                <p class="text-beige-600 max-w-2xl mx-auto">스탠리의 베스트셀러 제품을 만나보세요</p>
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                <!-- Product 1 -->
                <div class="group bg-white border border-beige-200 shadow-sm hover:shadow-lg transition-all duration-300 rounded-lg overflow-hidden">
                    <div class="relative overflow-hidden">
                        <img src="https://cncmall.kr/web/product/medium/202503/32098b7e34b3531cbaa223887c116df2.jpg"
                             alt="클래식 아이스플로우 플립 스트로 2.0 텀블러 591ml"
                             class="w-full h-80 object-cover group-hover:scale-105 transition-transform duration-500">
                        <div class="absolute top-4 left-4">
                            <span class="bg-gray-900 text-white px-2 py-1 text-xs rounded">NEW</span>
                        </div>
                        <div class="absolute top-4 right-4">
                            <div class="flex items-center gap-1 bg-white/90 px-2 py-1 rounded">
                                <i data-lucide="star" class="h-3 w-3 fill-yellow-400 text-yellow-400"></i>
                                <span class="text-xs font-medium">4.8</span>
                            </div>
                        </div>
                    </div>
                    <div class="p-6">
                        <h3 class="font-medium text-gray-900 mb-2 group-hover:text-beige-600 transition-colors">
                            클래식 아이스플로우 플립 스트로 2.0 텀블러 591ml
                        </h3>
                        <div class="flex items-center gap-2 mb-3">
                            <span class="text-xl font-light text-gray-900">₩44,000</span>
                        </div>
                        <div class="mb-4">
                            <div class="flex gap-1">
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-sky-400"></div>
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-pink-300"></div>
                            </div>
                        </div>
                        <a href="/productDetl?id=1" class="block w-full bg-gray-900 hover:bg-beige-800 text-white text-center py-2 rounded opacity-0 group-hover:opacity-100 transition-opacity duration-300">
                            상품 보기
                        </a>
                    </div>
                </div>

                <!-- Product 2 -->
                <div class="group bg-white border border-beige-200 shadow-sm hover:shadow-lg transition-all duration-300 rounded-lg overflow-hidden">
                    <div class="relative overflow-hidden">
                        <img src="https://cncmall.kr/web/product/medium/202503/19f846f87fa4f538f7d2e66da57533f7.jpg"
                             alt="퀜처 H2.0 플로우스테이트 텀블러 887ml 베리 트러플"
                             class="w-full h-80 object-cover group-hover:scale-105 transition-transform duration-500">
                        <div class="absolute top-4 right-4">
                            <div class="flex items-center gap-1 bg-white/90 px-2 py-1 rounded">
                                <i data-lucide="star" class="h-3 w-3 fill-yellow-400 text-yellow-400"></i>
                                <span class="text-xs font-medium">4.9</span>
                            </div>
                        </div>
                    </div>
                    <div class="p-6">
                        <h3 class="font-medium text-gray-900 mb-2 group-hover:text-beige-600 transition-colors">
                            퀜처 H2.0 플로우스테이트 텀블러 887ml 베리 트러플
                        </h3>
                        <div class="flex items-center gap-2 mb-3">
                            <span class="text-xl font-light text-gray-900">₩59,000</span>
                        </div>
                        <div class="mb-4">
                            <div class="flex gap-1">
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-purple-500"></div>
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-gray-800"></div>
                            </div>
                        </div>
                        <a href="/productDetl?id=2" class="block w-full bg-gray-900 hover:bg-beige-800 text-white text-center py-2 rounded opacity-0 group-hover:opacity-100 transition-opacity duration-300">
                            상품 보기
                        </a>
                    </div>
                </div>

                <!-- Product 3 -->
                <div class="group bg-white border border-beige-200 shadow-sm hover:shadow-lg transition-all duration-300 rounded-lg overflow-hidden">
                    <div class="relative overflow-hidden">
                        <img src="https://cncmall.kr/web/product/medium/202502/92dd9dac8eb279acf95ea34fcab01c26.jpg"
                             alt="카페투고 트래블머그 591ml"
                             class="w-full h-80 object-cover group-hover:scale-105 transition-transform duration-500">
                        <div class="absolute top-4 left-4">
                            <span class="bg-gray-900 text-white px-2 py-1 text-xs rounded">BEST</span>
                        </div>
                        <div class="absolute top-4 right-4">
                            <div class="flex items-center gap-1 bg-white/90 px-2 py-1 rounded">
                                <i data-lucide="star" class="h-3 w-3 fill-yellow-400 text-yellow-400"></i>
                                <span class="text-xs font-medium">4.7</span>
                            </div>
                        </div>
                    </div>
                    <div class="p-6">
                        <h3 class="font-medium text-gray-900 mb-2 group-hover:text-beige-600 transition-colors">
                            카페투고 트래블머그 591ml
                        </h3>
                        <div class="flex items-center gap-2 mb-3">
                            <span class="text-xl font-light text-gray-900">₩49,000</span>
                        </div>
                        <div class="mb-4">
                            <div class="flex gap-1">
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-brown-600"></div>
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-white"></div>
                            </div>
                        </div>
                        <a href="/productDetl?id=3" class="block w-full bg-gray-900 hover:bg-beige-800 text-white text-center py-2 rounded opacity-0 group-hover:opacity-100 transition-opacity duration-300">
                            상품 보기
                        </a>
                    </div>
                </div>

                <!-- Product 4 -->
                <div class="group bg-white border border-beige-200 shadow-sm hover:shadow-lg transition-all duration-300 rounded-lg overflow-hidden">
                    <div class="relative overflow-hidden">
                        <img src="https://cncmall.kr/web/product/medium/202506/e132d9607a719a21b6119a8ca30e419a.jpg"
                             alt="에어로라이트 보틀 591ml"
                             class="w-full h-80 object-cover group-hover:scale-105 transition-transform duration-500">
                        <div class="absolute top-4 right-4">
                            <div class="flex items-center gap-1 bg-white/90 px-2 py-1 rounded">
                                <i data-lucide="star" class="h-3 w-3 fill-yellow-400 text-yellow-400"></i>
                                <span class="text-xs font-medium">4.6</span>
                            </div>
                        </div>
                    </div>
                    <div class="p-6">
                        <h3 class="font-medium text-gray-900 mb-2 group-hover:text-beige-600 transition-colors">
                            에어로라이트 보틀 591ml
                        </h3>
                        <div class="flex items-center gap-2 mb-3">
                            <span class="text-xl font-light text-gray-900">₩46,000</span>
                        </div>
                        <div class="mb-4">
                            <div class="flex gap-1">
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-gray-500"></div>
                                <div class="w-4 h-4 rounded-full border border-beige-300 bg-lime-500"></div>
                            </div>
                        </div>
                        <a href="/productDetl?id=4" class="block w-full bg-gray-900 hover:bg-beige-800 text-white text-center py-2 rounded opacity-0 group-hover:opacity-100 transition-opacity duration-300">
                            상품 보기
                        </a>
                    </div>
                </div>
            </div>

            <div class="text-center mt-12">
                <button class="border border-gray-900 text-gray-900 hover:bg-gray-900 hover:text-white px-8 py-3 rounded-lg flex items-center gap-2 mx-auto">
                    전체 상품 보기
                    <i data-lucide="arrow-right" class="h-5 w-5"></i>
                </button>
            </div>
        </div>
    </section>


    <!-- Special Offer -->
    <section class="py-16 bg-gradient-to-r from-beige-200 to-beige-300">
        <div class="max-w-4xl mx-auto text-center px-4">
            <h2 class="text-4xl font-light text-gray-900 mb-4">2021 STANLEY S/S CATALOG</h2>
            <p class="text-xl text-beige-700 mb-8">	CNC GLOBAL </p>
            <div class="flex justify-center gap-4">
                <button class="bg-gray-900 hover:bg-beige-800 text-white px-8 py-3 rounded-lg">
                    VIEW MORE
                </button>
            </div>
        </div>
    </section>

    <!-- Features -->
    <section class="py-16 bg-white">
        <div class="max-w-7xl mx-auto px-4">
            <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
                <div class="text-center">
                    <div class="w-16 h-16 bg-beige-100 rounded-full flex items-center justify-center mx-auto mb-4">
                        <i data-lucide="truck" class="h-8 w-8 text-gray-900"></i>
                    </div>
                    <h3 class="text-lg font-medium text-gray-900 mb-2">무료 배송</h3>
                    <p class="text-beige-600">5만원 이상 구매 시 전국 무료배송</p>
                </div>
                <div class="text-center">
                    <div class="w-16 h-16 bg-beige-100 rounded-full flex items-center justify-center mx-auto mb-4">
                        <i data-lucide="refresh-cw" class="h-8 w-8 text-gray-900"></i>
                    </div>
                    <h3 class="text-lg font-medium text-gray-900 mb-2">무료 교환/반품</h3>
                    <p class="text-beige-600">30일 이내 무료 교환 및 반품 서비스</p>
                </div>
                <div class="text-center">
                    <div class="w-16 h-16 bg-beige-100 rounded-full flex items-center justify-center mx-auto mb-4">
                        <i data-lucide="shield" class="h-8 w-8 text-gray-900"></i>
                    </div>
                    <h3 class="text-lg font-medium text-gray-900 mb-2">품질 보증</h3>
                    <p class="text-beige-600">엄선된 소재와 완벽한 품질 관리</p>
                </div>
            </div>
        </div>
    </section>
</main>

<script>
    // Initialize Lucide icons
    lucide.createIcons();
</script>
